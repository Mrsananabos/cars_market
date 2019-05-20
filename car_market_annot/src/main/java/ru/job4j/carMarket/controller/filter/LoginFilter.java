package ru.job4j.carMarket.controller.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.lang.String.format;

public class LoginFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(LoginFilter.class);
    private static final String URL_AD = "/ad";
    private static final String URL_AUTHORIZ = "/authoriz";
    private static final String LOGIN = "login";
    private static final String STRING_FORMAT_S = "%s";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains(URL_AD)) {
            HttpSession session = request.getSession();
            if (session.getAttribute(LOGIN) == null) {
                ((HttpServletResponse) resp).sendRedirect(format(STRING_FORMAT_S + URL_AUTHORIZ, request.getContextPath()));
                return;
            }
        }
        chain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }

}
