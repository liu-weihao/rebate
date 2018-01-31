package com.dx.ss.data.rebate.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class RoleMenuModel {

    private Integer id;

    private Integer roleId;

    private Integer parentId;

    private Integer menuId;

    private String roleName;

    /**
     * 角色编码，纯英文字符
     */
    private String roleCode;

    private String menuName;

    private String icon;

    private String url;

    private Date gmtCreate;
}
