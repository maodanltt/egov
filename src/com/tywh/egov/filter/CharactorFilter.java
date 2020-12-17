package com.tywh.egov.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CharactorFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("GB18030");
        filterChain.doFilter(servletRequest, servletResponse);

        //System.out.println(servletRequest instanceof HttpServletRequest);
    }

    @Override
    public void destroy() {

    }
}
