package com.qiquinn.verification.filter;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author:QiQuinn
 * @Desicription:
 * @Date:Created in 2019/7/29
 * @Modified By:
 */
//@Component
public class TimeFilter implements Filter {
    private static final Logger log = LoggerFactory.getLogger(Filter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Filter init");
    }
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter");
        filterChain.doFilter(servletRequest,servletResponse);
    }
    @Override
    public void destroy() {
        log.info("destroy");
    }
}
