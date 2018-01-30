package com.dx.ss.data.rebate.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class IndexController {

    @RequestMapping(value = "/")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) {
        return "login";
    }

    @RequestMapping(value = "/main")
    public String toIndex(HttpServletRequest request, HttpServletResponse response) {
        return "main";
    }


    @RequestMapping(value = "/test", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.GET)
    public String test(HttpServletRequest request, HttpServletResponse response) {

        return "{\"status\": 200, \"body\":{\"pageNum\": 1, \"pageSize\": 10, \"pages\": 13, \"total\":135, \"dataList\":[{\"name\":\"Lily\"},{\"name\":\"小龙\"}]}}";

    }
}
