package com.dx.ss.data.rebate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/")
public class MenuController {

    @RequestMapping(value = "left")
    public String leftMenu(HttpServletRequest request, HttpServletResponse response) {

        return "left";
    }

    @RequestMapping(value = "right")
    public String rightMenu(HttpServletRequest request, HttpServletResponse response) {

        return "right";
    }

    @RequestMapping(value = "top")
    public String topMenu(HttpServletRequest request, HttpServletResponse response) {
        return "top";
    }

    @RequestMapping(value = "welcome")
    public String welcome(HttpServletRequest request, HttpServletResponse response) {
        return "index";
    }
}
