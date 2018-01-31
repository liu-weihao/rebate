package com.dx.ss.data.rebate.service;

import com.dx.ss.data.rebate.dal.beans.RoleMenu;
import com.dx.ss.data.rebate.dal.mapper.RoleMenuMapper;
import com.dx.ss.data.rebate.model.RoleMenuModel;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    public boolean add(RoleMenu roleMenu) {
        return roleMenu != null && roleMenuMapper.insertSelective(roleMenu) == 1;
    }

    public List<RoleMenuModel> getRoleMenus(Integer roleId) {
        if(roleId == null) return Lists.newArrayList();
        return roleMenuMapper.getRoleMenus(roleId);
    }

    public int deleteByRole(Integer roleId) {
        if(roleId == null) return 0;
        Example ex = new Example(RoleMenu.class);
        ex.createCriteria().andEqualTo("roleId", roleId);
        return roleMenuMapper.deleteByExample(ex);
    }

    public int deleteByBatch(List<Integer> ids) {
        if(CollectionUtils.isEmpty(ids)) return 0;
        Example ex = new Example(RoleMenu.class);
        ex.createCriteria().andIn("id", ids);
        return roleMenuMapper.deleteByExample(ex);
    }
}
