package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.model.RoleMenuModel;
import com.dx.ss.data.rebate.service.MenuService;
import com.dx.ss.data.rebate.vo.ResponseObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(value = "left", produces = { "application/json;charset=UTF-8" })
    public ResponseObj leftMenu(@RequestParam(name = "roleId") Integer roleId) {
        List<RoleMenuModel> menus = menuService.getRoleMenus(roleId);
        return ResponseObj.success(menus);
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
