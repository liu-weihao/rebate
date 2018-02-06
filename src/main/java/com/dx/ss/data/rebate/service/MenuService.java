package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.dal.beans.Menu;
import com.dx.ss.data.rebate.dal.beans.RoleMenu;
import com.dx.ss.data.rebate.dal.mapper.MenuMapper;
import com.dx.ss.data.rebate.dal.mapper.RoleMenuMapper;
import com.dx.ss.data.rebate.form.RoleMenuForm;
import com.dx.ss.data.rebate.model.MenuModel;
import com.dx.ss.data.rebate.model.RoleMenuModel;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;


    public boolean add(RoleMenu roleMenu) {
        return roleMenu != null && roleMenuMapper.insertSelective(roleMenu) == 1;
    }

    public List<MenuModel> getMenuList() {
        List<Menu> allMenus = menuMapper.selectAll();
        List<MenuModel> menuList = new ArrayList<>();
        for (Menu menu : allMenus) {
            MenuModel menuModel = new MenuModel();
            BeanUtils.copyProperties(menu, menuModel);
            Integer menuId = menu.getId();
            if (menu.getParentId() > 0) continue;
            for (Menu m : allMenus) {
                if (menuId.equals(m.getParentId())) {
                    MenuModel child = new MenuModel();
                    BeanUtils.copyProperties(m, child);
                    menuModel.addChild(child);
                }
            }
            menuList.add(menuModel);
        }
        return menuList;
    }

    public List<MenuModel> getRoleMenus(Integer roleId) {
        if (roleId == null) return Lists.newArrayList();
        List<MenuModel> menuList = new ArrayList<>();
        List<RoleMenuModel> roleMenus = roleMenuMapper.getRoleMenus(roleId);
        for (RoleMenuModel roleMenu : roleMenus) {
            MenuModel menu = new MenuModel();
            BeanUtils.copyProperties(roleMenu, menu);
            Integer menuId = roleMenu.getMenuId();
            menu.setId(menuId);
            if (roleMenu.getParentId() > 0) continue;
            for (RoleMenuModel m : roleMenus) {
                if (menuId.equals(m.getParentId())) {
                    MenuModel child = new MenuModel();
                    BeanUtils.copyProperties(m, child);
                    menu.addChild(child);
                }
            }
            menuList.add(menu);
        }
        return menuList;
    }

    public int addRoleMenus(final RoleMenuForm form) {
        deleteByRole(form.getRoleId()); //先删除权限

        List<RoleMenu> list = new ArrayList<>();
        List<Integer> menuIds = form.getMenuId();
        menuIds.forEach(menuId -> {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(form.getRoleId());
            roleMenu.setMenuId(menuId);
            list.add(roleMenu);
        });
        return roleMenuMapper.insertList(list);
    }

    public int deleteByRole(Integer roleId) {
        if (roleId == null) return 0;
        Example ex = new Example(RoleMenu.class);
        ex.createCriteria().andEqualTo("roleId", roleId);
        return roleMenuMapper.deleteByExample(ex);
    }

    public int deleteByBatch(List<Integer> ids) {
        if (CollectionUtils.isEmpty(ids)) return 0;
        Example ex = new Example(RoleMenu.class);
        ex.createCriteria().andIn("id", ids);
        return roleMenuMapper.deleteByExample(ex);
    }
}
