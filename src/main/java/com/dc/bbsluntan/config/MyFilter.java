package com.dc.bbsluntan.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

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

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        log.info("拦截到:{}",((HttpServletRequest) req).getServletPath());
        String path = ((HttpServletRequest) req).getServletPath();


        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, GET");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        String[] urls = {".js",".css",".png","login.html",".jpg",".png",".php","/login","/register"};
        boolean flag = true;
        for (String str : urls) {
            if (path.indexOf(str) != -1) {
                flag =false;
                break;
            }
        }

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
        Long id = (Long) request.getSession().getAttribute("user");
        if(id !=null )
            log.info("用户:{},已登录",id);
/*        if(id != null)
            chain.doFilter(req,res);*/
        //response.sendRedirect(request.getContextPath()+"/front/pages/login.html");
        chain.doFilter(req,response);
    }

}
