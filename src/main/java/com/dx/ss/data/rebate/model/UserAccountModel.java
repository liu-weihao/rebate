package com.dx.ss.data.rebate.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class UserAccountModel {

    /**
     * 账户id
     */
    private Integer accountId;

    /**
     * 账户名称
     */
    private String accountName;

    /**
     * 联盟名称
     */
    private String unionName;

    /**
     * 推广位
     */
    private String recommend;

    /**
     * 用户id
     */
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

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 创建时间
     */
    private Date gmtCreate;
}
