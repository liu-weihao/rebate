package com.dx.ss.data.rebate.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MenuModel {

    private Integer id;

    private String menuName;

    private String icon;

    private String url;

    private List<MenuModel> childList = new ArrayList<>();

    public void addChild(MenuModel menu) {
        childList.add(menu);
    }
}
