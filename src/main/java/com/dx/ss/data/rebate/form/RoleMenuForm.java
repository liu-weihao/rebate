package com.dx.ss.data.rebate.form;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class RoleMenuForm {

    private Integer roleId;

    private List<Integer> menuId;
}
