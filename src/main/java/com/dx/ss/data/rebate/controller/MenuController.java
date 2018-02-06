package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.enums.StatusCode;
import com.dx.ss.data.rebate.form.RoleMenuForm;
import com.dx.ss.data.rebate.service.MenuService;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/")
public class MenuController extends BaseController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "menus.web", produces = { "application/json;charset=UTF-8" })
    public ResponseObj menus(HttpServletRequest request, Integer roleId) {
        if(roleId == null) {
            roleId = super.roleId(request);
        }
        return ResponseObj.success(menuService.getRoleMenus(roleId));
    }

    @RequestMapping(value = "allMenus.web", produces = { "application/json;charset=UTF-8" })
    public ResponseObj allMenus() {
        return ResponseObj.success(menuService.getMenuList());
    }


    @RequestMapping(value = "addRoleMenus.web", produces = { "application/json;charset=UTF-8" }, method = RequestMethod.POST)
    public ResponseObj addRoleMenus(@Valid RoleMenuForm form, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseObj.fail(StatusCode.FORM_INVALID, result.getAllErrors().get(0).getDefaultMessage());
        }
        return ResponseObj.success(menuService.addRoleMenus(form));
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
