package com.dx.ss.data.rebate.dal.beans;

import java.util.Date;
import javax.persistence.*;

@Table(name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    @Column(name = "user_id")
    private Integer userId;

    /**
     * main-主号，sub-附号
     */
    private String type;

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
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return account_id
     */
    public Integer getAccountId() {
        return accountId;
    }

    /**
     * @param accountId
     */
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    /**
     * @return user_id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取main-主号，sub-附号
     *
     * @return type - main-主号，sub-附号
     */
    public String getType() {
        return type;
    }

    /**
     * 设置main-主号，sub-附号
     *
     * @param type main-主号，sub-附号
     */
    public void setType(String type) {
        this.type = type;
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