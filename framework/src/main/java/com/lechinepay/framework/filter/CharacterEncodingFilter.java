package com.lechinepay.framework.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {

    public static final String DEFAULT_ENCODING = "UTF-8";

    private String             encoding;

    public void destroy() {
    }

    public void init(FilterConfig paramFilterConfig) {
        encoding = paramFilterConfig.getInitParameter("encoding");
        if (null == encoding || encoding.trim().equals("")) {
            encoding = DEFAULT_ENCODING;
        }
    }

    public void doFilter(ServletRequest paramServletRequest, ServletResponse paramServletResponse,
            FilterChain paramFilterChain) throws ServletException, IOException {
        paramServletRequest.setCharacterEncoding(encoding);
        paramFilterChain.doFilter(paramServletRequest, paramServletResponse);
    }

}
