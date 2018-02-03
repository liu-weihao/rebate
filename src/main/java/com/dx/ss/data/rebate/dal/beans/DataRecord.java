package com.dx.ss.data.rebate.dal.beans;

import com.dx.ss.data.rebate.annotation.Domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Domain
@Table(name = "data_record")
public class DataRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_id")
    private Integer accountId;

    /**
     * 录入日期
     */
    @Column(name = "record_date")
    private Date recordDate;

    /**
     * 订单数量
     */
    @Column(name = "order_num")
    private int orderNum;

    /**
     * 粉丝数量
     */
    @Column(name = "fans_num")
    private int fansNum;

    /**
     * 成交预估
     */
    @Column(name = "deal_num")
    private BigDecimal dealNum;

    /**
     * 结算预估
     */
    @Column(name = "settle_num")
    private BigDecimal settleNum;

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
     * 获取录入日期
     *
     * @return record_date - 录入日期
     */
    public Date getRecordDate() {
        return recordDate;
    }

    /**
     * 设置录入日期
     *
     * @param recordDate 录入日期
     */
    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    /**
     * 获取订单数量
     *
     * @return order_num - 订单数量
     */
    public int getOrderNum() {
        return orderNum;
    }

    /**
     * 设置订单数量
     *
     * @param orderNum 订单数量
     */
    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    /**
     * 获取粉丝数量
     *
     * @return fans_num - 粉丝数量
     */
    public int getFansNum() {
        return fansNum;
    }

    /**
     * 设置粉丝数量
     *
     * @param fansNum 粉丝数量
     */
    public void setFansNum(int fansNum) {
        this.fansNum = fansNum;
    }

    /**
     * 获取成交预估
     *
     * @return deal_num - 成交预估
     */
    public BigDecimal getDealNum() {
        return dealNum;
    }

    /**
     * 设置成交预估
     *
     * @param dealNum 成交预估
     */
    public void setDealNum(BigDecimal dealNum) {
        this.dealNum = dealNum;
    }

    /**
     * 获取结算预估
     *
     * @return settle_num - 结算预估
     */
    public BigDecimal getSettleNum() {
        return settleNum;
    }

    /**
     * 设置结算预估
     *
     * @param settleNum 结算预估
     */
    public void setSettleNum(BigDecimal settleNum) {
        this.settleNum = settleNum;
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