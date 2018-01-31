package com.dx.ss.data.rebate.controller;

import com.dx.ss.data.rebate.dal.beans.RoleInfo;
import com.dx.ss.data.rebate.service.RoleService;
import com.dx.ss.data.rebate.vo.GridObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/web/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @RequestMapping(value = "/list.web", produces = { "application/json;charset=UTF-8" })
    public GridObj<RoleInfo> getRoles() {

        return GridObj.of(roleService.getRoles());
    }
}
