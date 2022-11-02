package com.dc.bbsluntan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DengChao
 */
@RestController
public class IndexController {
    @GetMapping("/")
    public String index(){return "index";}


}
