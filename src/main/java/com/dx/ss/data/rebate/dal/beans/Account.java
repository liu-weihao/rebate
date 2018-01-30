package com.dx.ss.data.rebate.dal.beans;

import com.dx.ss.data.rebate.annotation.Domain;

import java.util.Date;
import javax.persistence.*;

@Domain
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 微信名称
     */
    @Column(name = "account_name")
    private String accountName;

    /**
     * 联盟名称
     */
    @Column(name = "union_name")
    private String unionName;

    /**
     * 推广位
     */
    private String recommend;

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
     * 获取微信名称
     *
     * @return account_name - 微信名称
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 设置微信名称
     *
     * @param accountName 微信名称
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * 获取联盟名称
     *
     * @return union_name - 联盟名称
     */
    public String getUnionName() {
        return unionName;
    }

    /**
     * 设置联盟名称
     *
     * @param unionName 联盟名称
     */
    public void setUnionName(String unionName) {
        this.unionName = unionName;
    }

    /**
     * 获取推广位
     *
     * @return recommend - 推广位
     */
    public String getRecommend() {
        return recommend;
    }

    /**
     * 设置推广位
     *
     * @param recommend 推广位
     */
    public void setRecommend(String recommend) {
        this.recommend = recommend;
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