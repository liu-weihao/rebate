package com.dx.ss.data.rebate.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserAccountModel {

    private Integer id;

    private Integer accountId;

    private String accountName;

    /**
     * 联盟名称
     */
    private String unionName;

    /**
     * 推广位
     */
    private String recommend;

    private String userId;

    /**
     * main-主号，sub-附号
     */
    private String type;

    /**
     * 用户名，登陆账号，也作为联系方式
     */
    private String username;

    /**
     * 手机号码
     */
    private String phone;

    private String name;

}
