package com.dx.ss.data.rebate.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserModel {

    private String userId;

    /**
     * 用户名，登陆账号，也作为联系方式
     */
    private String username;

    private String nickname;

    /**
     * 手机号码
     */
    private String phone;

    private String name;

    /**
     * 工龄
     */
    private Integer workAge;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色编码，纯英文字符
     */
    private String roleCode;


    /**
     * 用户来源：10-导入，20-APP注册，30-后台添加
     */
    private Integer userFrom;

    /**
     * 用户类型:0-（super_admin）超级管理员，10（USER_WEB）-后端用户
     */
    private Integer type;

    /**
     * 账号状态:10-正常，20-冻结，30-拉黑
     */
    private Integer status;

    private Date gmtCreate;
}
