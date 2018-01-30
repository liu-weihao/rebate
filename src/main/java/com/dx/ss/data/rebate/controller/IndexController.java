package com.dx.ss.data.rebate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @RequestMapping(value = "/main")
    public String toIndex(HttpServletRequest request, HttpServletResponse response) {
        return "main";
    }
}
