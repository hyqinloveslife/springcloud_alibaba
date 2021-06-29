package com.hyqin.config.interceptor;

import com.hyqin.util.RequestWrapper;
import com.hyqin.util.SysLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description 自定义拦截器
 * @author: huangyeqin
 * @create : 2021/6/22  20:59
 */
@Slf4j
@Component
public class RequestUrlInterceptor implements HandlerInterceptor {
    private long startTime = 0;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info(">>>>>请求开始--Url : {} >>>>>>>", request.getRequestURI());
        startTime = System.currentTimeMillis();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info(">>>>>请求结束>>>>>>");
        RequestWrapper requestWrapper = new RequestWrapper(request);
        SysLogUtil.saveLog(request, ex, requestWrapper.getBody(), (System.currentTimeMillis() - startTime));
        if (ex != null) {
            log.error("报错信息: {}", ex);
        }
    }
}
