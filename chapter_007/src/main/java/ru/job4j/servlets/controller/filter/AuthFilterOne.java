package ru.job4j.servlets.controller.filter;


import ru.job4j.servlets.model.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;
import static ru.job4j.servlets.model.Role.user;
import static ru.job4j.servlets.model.Role.admin;

public class AuthFilterOne implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getRequestURI().contains("/signin")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("role") == null) {
                ((HttpServletResponse) resp).sendRedirect(String.format("%s/signin", request.getContextPath()));
                return;
            }
            chain.doFilter(req, resp);
        }
    }


    @Override
    public void destroy() {

    }
}
