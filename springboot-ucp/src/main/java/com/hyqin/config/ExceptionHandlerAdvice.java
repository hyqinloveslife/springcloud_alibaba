package com.hyqin.config;

import com.hyqin.domain.config.R;
import com.hyqin.domain.config.ResponseEnum;
import com.hyqin.exception.BusinessException;
import com.hyqin.util.SysLogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @description 捕获全局异常
 * @author: huangyeqin
 * @create : 2021/6/23  8:16
 */

@Slf4j
@ControllerAdvice(
        annotations = RestController.class
)
public class ExceptionHandlerAdvice {

    /**
     * @Desc : 定义通用异常捕获handler
     * @Author : huangyeqin
     * @Date : 2021/6/23 8:23
     * @Param : request
     * @param: e
     * @Result : com.hyqin.domain.config.R
     **/
    @ExceptionHandler(Exception.class)
    public R exceptionHandle(HttpServletRequest request, Exception e) {
        return resultFormat(request, ResponseEnum.RESPONSE_CODE_FAIL, e);
    }

    /**
     * @Desc : 通用异常处理
     * @Author : huangyeqin
     * @Date : 2021/6/23 8:57
     * @Param : request
     * @param: t
     * @Result : com.hyqin.domain.config.R
     **/
    @ExceptionHandler(Throwable.class)
    public R throwableHandler(HttpServletRequest request, Throwable t) {
        return resultFormat(request, ResponseEnum.RESPONSE_CODE_FAIL, (Exception) t);
    }

    /**
     * @Desc : 404 错误
     * @Author : huangyeqin
     * @Date : 2021/6/23 8:52
     * @Param : request
     * @param: e
     * @Result : com.hyqin.domain.config.R
     **/
    @ExceptionHandler(NoHandlerFoundException.class)
    public R request404(HttpServletRequest request, NoHandlerFoundException e) {
        return resultFormat(request, ResponseEnum.RESPONSE_CODE_NOT_FOUND_404, e);
    }

    /**
     * @Desc : 405 方法不支持
     * @Author : huangyeqin
     * @Date : 2021/6/23 8:55
     * @Param : request
     * @param: e
     * @Result : com.hyqin.domain.config.R
     **/
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R request405(HttpServletRequest request, HttpRequestMethodNotSupportedException e) {
        return resultFormat(request, ResponseEnum.RESPONSE_CODE_METHOD_NOT_SUPPORT_405, e);
    }

    /**
     * @Desc : 内部程序异常抛出
     * @Author : huangyeqin
     * @Date : 2021/6/30 9:16
     * @Param : request
     * @param: e
     * @param: responseEnum
     * @Result : com.hyqin.domain.config.R
     **/
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.OK)
    public R handleBusinessException(HttpServletRequest request, Exception e) {
        return resultFormat(request, e);
    }

    private R resultFormat(HttpServletRequest request, Exception e) {
        log.error("********************Throw Exception.url:{} ERROR:{}********************", request.getRequestURL(), e.getMessage(), e);
        R result = new R();
        if (e instanceof BusinessException) {
            BusinessException be = (BusinessException) e;

            String traceId = UUID.randomUUID().toString();
            result.setMessage(be.getErrorMsg());
            result.setCode(be.getErrorCode());
            result.setType(R.ResponseType.TYPE_ERROR.getType());

            // 保存异常信息到数据库(后期这里可以改成用消息队列的方法，走异步的比较快)
            SysLogUtil.saveErrorLog(request, e, traceId, String.valueOf(be.getErrorCode()), be.getErrorMsg());
        }
        return result;
    }

    /**
     * 返回格式封装
     *
     * @param request
     * @param responseEnum
     * @param e
     * @return com.openailab.oascloud.common.model.ResponseResult
     * @author zxzhang
     * @date 2019/6/27
     */
    private R resultFormat(HttpServletRequest request, ResponseEnum responseEnum, Exception e) {
        log.error("********************Throw Exception.url:{} ERROR:{}********************", request.getRequestURL(), e.getMessage(), e);
        String traceId = UUID.randomUUID().toString();
        R result = new R();
        result.setMessage("{" + traceId + "} :" + e.getMessage());
        result.setCode(responseEnum.getCode());
        result.setType(R.ResponseType.TYPE_ERROR.getType());

        // 保存异常信息到数据库(后期这里可以改成用消息队列的方法，走异步的比较快)
        SysLogUtil.saveErrorLog(request, e, traceId, String.valueOf(responseEnum.getCode()), responseEnum.getMsg());
        return result;
    }
}
