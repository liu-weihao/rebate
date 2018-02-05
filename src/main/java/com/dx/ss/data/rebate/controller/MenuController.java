package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.service.MenuService;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
