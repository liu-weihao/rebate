package com.dx.ss.data.rebate.dal.beans;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_info")
public class UserInfo {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
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
    @Column(name = "work_age")
    private Integer workAge;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 密码
     */
    private String password;

    /**
     * 用户来源：10-导入，20-APP注册，30-后台添加
     */
    @Column(name = "user_from")
    private Integer userFrom;

    /**
     * 用户类型:0-（super_admin）超级管理员，10（USER_WEB）-后端用户
     */
    private Integer type;

    /**
     * 账号状态:10-正常，20-冻结，30-拉黑
     */
    private Integer status;

    /**
     * 是否删除：1-是，0-否
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    @Column(name = "gmt_create")
    private Date gmtCreate;

    @Column(name = "gmt_modify")
    private Date gmtModify;

    /**
     * @return user_id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名，登陆账号，也作为联系方式
     *
     * @return username - 用户名，登陆账号，也作为联系方式
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名，登陆账号，也作为联系方式
     *
     * @param username 用户名，登陆账号，也作为联系方式
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取手机号码
     *
     * @return phone - 手机号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号码
     *
     * @param phone 手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取工龄
     *
     * @return work_age - 工龄
     */
    public Integer getWorkAge() {
        return workAge;
    }

    /**
     * 设置工龄
     *
     * @param workAge 工龄
     */
    public void setWorkAge(Integer workAge) {
        this.workAge = workAge;
    }

    /**
     * 获取头像
     *
     * @return avatar - 头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     *
     * @param avatar 头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户来源：10-导入，20-APP注册，30-后台添加
     *
     * @return user_from - 用户来源：10-导入，20-APP注册，30-后台添加
     */
    public Integer getUserFrom() {
        return userFrom;
    }

    /**
     * 设置用户来源：10-导入，20-APP注册，30-后台添加
     *
     * @param userFrom 用户来源：10-导入，20-APP注册，30-后台添加
     */
    public void setUserFrom(Integer userFrom) {
        this.userFrom = userFrom;
    }

    /**
     * 获取用户类型:0-（super_admin）超级管理员，10（USER_WEB）-后端用户
     *
     * @return type - 用户类型:0-（super_admin）超级管理员，10（USER_WEB）-后端用户
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置用户类型:0-（super_admin）超级管理员，10（USER_WEB）-后端用户
     *
     * @param type 用户类型:0-（super_admin）超级管理员，10（USER_WEB）-后端用户
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取账号状态:10-正常，20-冻结，30-拉黑
     *
     * @return status - 账号状态:10-正常，20-冻结，30-拉黑
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置账号状态:10-正常，20-冻结，30-拉黑
     *
     * @param status 账号状态:10-正常，20-冻结，30-拉黑
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取是否删除：1-是，0-否
     *
     * @return is_deleted - 是否删除：1-是，0-否
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除：1-是，0-否
     *
     * @param isDeleted 是否删除：1-是，0-否
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return gmt_create
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * @param gmtCreate
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * @return gmt_modify
     */
    public Date getGmtModify() {
        return gmtModify;
    }

    /**
     * @param gmtModify
     */
    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }
}