package com.hyqin.util;

import cn.hutool.core.lang.Assert;
import cn.hutool.extra.spring.SpringUtil;
import com.hyqin.dao.SysErrorLogDao;
import com.hyqin.dao.SysLogDao;
import com.hyqin.entity.SysErrorLog;
import com.hyqin.entity.SysLog;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

/**
 * @description
 * @author: huangyeqin
 * @create : 2021/6/22  21:13
 */
public class SysLogUtil {

    private static SysLogDao sysLogDao = SpringUtil.getApplicationContext().getBean(SysLogDao.class);

    private static SysErrorLogDao errorLogDao = SpringUtil.getApplicationContext().getBean(SysErrorLogDao.class);

    /**
     * @Desc : 保存基本操作日志
     * @Author : huangyeqin
     * @Date : 2021/6/22 21:21
     * @Param : request
     * @param: ex
     * @param: title
     * @param: time
     * @Result : void
     **/
    public static void saveLog(HttpServletRequest request, Exception ex, String params, Long time) throws IOException {
        SysLog sysLogBean = new SysLog();
        sysLogBean.setCreatedTime(new Date());
        // 创建人，需要集成用户权限框架 sa-tokens
        sysLogBean.setCreatedBy(10000L);
        sysLogBean.setIp(getRemoteAddr(request));
        sysLogBean.setTime(time);
        sysLogBean.setParams(strPatter(params));
        sysLogBean.setMethod(request.getMethod());
        sysLogBean.setOperation(request.getRequestURI());
        int result = sysLogDao.insertSelective(sysLogBean);
        Assert.isTrue(result == 1, "添加系统日志失败");
    }

    /**
     * @Desc : 保存系统异常日志
     * @Author : huangyeqin
     * @Date : 2021/6/23 9:17
     * @Param : request
     * @param: t
     * @param: traceId
     * @param: errCode
     * @param: errMsg
     * @Result : void
    **/
    public static void saveErrorLog(HttpServletRequest request, Exception t, String traceId, String errCode, String errMsg) {
        SysErrorLog sysErrorLog = new SysErrorLog();
        sysErrorLog.setCreatedTime(new Date());
        sysErrorLog.setCreatedBy(10000L);
        sysErrorLog.setErrMsg(errMsg);
        sysErrorLog.setErrNo(errCode);
        sysErrorLog.setServerIp(getRemoteAddr(request));
        sysErrorLog.setStackTrace(getStackTrace(t));
        sysErrorLog.setSno(traceId);
        sysErrorLog.setExceptionName(t.getMessage());
        sysErrorLog.setUrl(request.getRequestURL().toString());

        int result = errorLogDao.insertSelective(sysErrorLog);
        Assert.isTrue(result == 1, "保存系统异常日志失败");
    }

    /**
     * @Desc : 获取完整的堆栈信息
     * @Author : huangyeqin
     * @Date : 2021/6/23 10:02
     * @Param : t
     * @Result : java.lang.String
    **/
    private static String getStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)){
            t.printStackTrace(pw);
            return sw.toString();
        }
    }

    private static String strPatter(String content) {
        content = content.replaceAll("\\\n", "");
        content = content.replaceAll("\\\t", "");
        content = content.replaceAll(" ", "");
        return content;
    }

    /**
     * 获得用户远程地址
     */
    private static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip) || "null".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
