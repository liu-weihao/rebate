package com.dx.ss.data.rebate.dal.mapper;

import com.dx.ss.data.rebate.dal.beans.RoleMenu;
import com.dx.ss.data.rebate.model.RoleMenuModel;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface RoleMenuMapper extends Mapper<RoleMenu> {

    List<RoleMenuModel> getRoleMenus(@Param("roleId") Integer roleId);

}