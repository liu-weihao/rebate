package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.condition.search.UserListSearch;
import com.dx.ss.data.rebate.constants.ViewConstants;
import com.dx.ss.data.rebate.dal.beans.UserInfo;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.form.UserForm;
import com.dx.ss.data.rebate.helper.ServletHelper;
import com.dx.ss.data.rebate.pager.BasePager;
import com.dx.ss.data.rebate.pager.WebPager;
import com.dx.ss.data.rebate.service.UserService;
import com.dx.ss.data.rebate.vo.GridObj;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
        ResponseObj obj = userService.login(username, password);
        if (obj.isSuccess()) {
            ServletHelper.getRequest().getSession().setAttribute(ViewConstants.LOGIN_TICKET_USER, obj.getBody());
        }
        return obj;
    }

    @RequestMapping(value = "/list.web", produces = { "application/json;charset=UTF-8" })
    public GridObj<UserInfo> getUserList(UserListSearch search) {
        BasePager<UserInfo> userList = userService.getUserList(search);
        return GridObj.of((WebPager<UserInfo>)userList);
    }


    @RequestMapping(value = "/addUser.web", produces = { "application/json;charset=UTF-8" })
    public ResponseObj addUser(@Valid UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseObj.fail(StatusCode.FORM_INVALID, result.getAllErrors().get(0).getDefaultMessage());
        }
        if(userService.addUserInfo(userForm)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }
}
