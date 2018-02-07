package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.condition.search.UserListSearch;
import com.dx.ss.data.rebate.constants.ViewConstants;
import com.dx.ss.data.rebate.dal.beans.UserInfo;
import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.form.UserForm;
import com.dx.ss.data.rebate.helper.ServletHelper;
import com.dx.ss.data.rebate.model.UserModel;
import com.dx.ss.data.rebate.pager.BasePager;
import com.dx.ss.data.rebate.pager.WebPager;
import com.dx.ss.data.rebate.service.UserService;
import com.dx.ss.data.rebate.vo.GridObj;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/web/user")
public class UserController extends BaseController {

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
    public GridObj<UserModel> getUserList(UserListSearch search) {
        BasePager<UserModel> userList = userService.getUserList(search);
        return GridObj.of((WebPager<UserModel>)userList);
    }

    @RequestMapping(value = "/all.web", produces = { "application/json;charset=UTF-8" })
    public ResponseObj getAllUsers() {
        return ResponseObj.success(userService.getUserList());
    }

    @RequestMapping(value = "/getUnassignedUsers.web", produces = { "application/json;charset=UTF-8" })
    public ResponseObj getUnassignedUsers() {
        return ResponseObj.success(userService.getUnassignedUsers());
    }

    @RequestMapping(value = "/info.web", produces = { "application/json;charset=UTF-8" })
    public ResponseObj addUser(HttpServletRequest request) {
        return ResponseObj.success(userService.getUserInfo(super.userId(request)));
    }

    @RequestMapping(value = "/addUser.web", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
    public ResponseObj addUser(@Valid UserForm userForm, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseObj.fail(StatusCode.FORM_INVALID, result.getAllErrors().get(0).getDefaultMessage());
        }
        if (CollectionUtils.isNotEmpty(userService.getUserByUsername(userForm.getUsername()))) {
            return ResponseObj.fail(StatusCode.BIZ_FAILED, "用户名已存在");
        }
        if (userService.addUserInfo(userForm)) return ResponseObj.success();
        return ResponseObj.fail();
    }

    @RequestMapping(value = "/editUser.web", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
    public ResponseObj editUser(@RequestParam(name = "userId") String userId, @RequestParam(name = "name") String name,
                               @RequestParam(name = "workAge") Integer workAge, @RequestParam(name = "phone") String phone,
                               @RequestParam(name = "roleId") Integer roleId) {
        if (userService.editUserInfo(userId, name, workAge, phone, roleId)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }

    @RequestMapping(value = "/changeUserStatus.web", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public ResponseObj changeUserStatus(@RequestParam(name = "userId") String userId, @RequestParam(name = "status") Integer status) {

        if (userService.changeUserStatus(userId, status)) {
            return ResponseObj.success();
        }
        return ResponseObj.fail();
    }

    @RequestMapping(value = "/logout.do", produces = {"application/json;charset=UTF-8"}, method = RequestMethod.POST)
    public ResponseObj logout(HttpServletRequest request) {
        request.getSession().removeAttribute(ViewConstants.LOGIN_TICKET_USER);
        return ResponseObj.success();
    }
}
