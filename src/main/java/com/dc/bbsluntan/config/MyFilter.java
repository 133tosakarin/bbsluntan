package com.dc.bbsluntan.config;

import com.alibaba.fastjson.JSON;
import com.dc.bbsluntan.common.R;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DengChao
 */
@Component
@Slf4j
public class MyFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        log.info("拦截到:{}", ((HttpServletRequest) req).getServletPath());
        //String path = ((HttpServletRequest) req).getServletPath();
        String reqPath = request.getRequestURI();

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
        if("OPTIONS".equalsIgnoreCase(((HttpServletRequest)request).getMethod())){
            response.setStatus(HttpServletResponse.SC_OK);
        }else{
            chain.doFilter(request,response);
        }
        String[] urls = new String[]{
                "/user/login",
                "/user/logout",
                "/backend/**",
                "/front/**",
        };
        boolean check = check(urls, reqPath);

        /*if (!flag) {
            log.info("允许通过");
            Long id = (Long) request.getSession().getAttribute("user");
            if(id != null)
                chain.doFilter(req,res);
            else
                response.sendRedirect("/front/pages/login.html");

        *//*else{
            response.sendRedirect(request.getContextPath()+"/front/pages/login.html");
        }*/

        if (check) {
            log.info("放行:{}",request.getRequestURI());
            chain.doFilter(request, response);
            return;
        }

        Long id = (Long) request.getSession().getAttribute("user");
        if (id != null) {
            log.info("用户:{},已登录", id);
            log.info("放行:{}",request.getRequestURI());
            chain.doFilter(request, response);
            return;
        }
        //response.sendRedirect("http://localhost:8080/front/pages/login.html");
/*        if(id != null)
            chain.doFilter(req,res);*/
        //response.sendRedirect(request.getContextPath()+"/front/pages/login.html");
        //response.getOutputStream().write(JSON.toJSONBytes("NOTLOGIN"));
        //chain.doFilter(req,response);
    }

    public boolean check(String[] urls, String requestURI) {
        for (String url : urls) {
            //把浏览器发过来的请求和我们定义的不拦截的url做比较，匹配则放行
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match) {
                return true;
            }
        }
        return false;

    }
}