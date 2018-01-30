package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.service.UserService;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/web/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/login.do", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
    public ResponseObj doLogin(String username, String password) {
        if (StringUtils.isBlank(username)) {
            return ResponseObj.fail(StatusCode.PARAM_BLANK, "请输入登录用户名");
        }
        if (StringUtils.isBlank(password)) {
            return ResponseObj.fail(StatusCode.PARAM_BLANK, "请输入登录密码");
        }
        return userService.login(username, password);
    }
}
