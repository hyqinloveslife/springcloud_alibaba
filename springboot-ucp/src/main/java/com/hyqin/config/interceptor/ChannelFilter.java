package com.hyqin.config.interceptor;

import com.hyqin.util.RequestWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description 拦截器链
 * @author: huangyeqin
 * @create : 2021/6/22  22:30
 */
@WebFilter(urlPatterns = "/*",filterName = "channelFilter")
public class ChannelFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ServletRequest requestWrapper = null;
        if (servletRequest instanceof HttpServletRequest){
            requestWrapper = new RequestWrapper((HttpServletRequest) servletRequest);
        }
        if (requestWrapper == null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            filterChain.doFilter(requestWrapper,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
