package com.dx.ss.data.rebate.dal.beans;

import com.dx.ss.data.rebate.annotation.Domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Domain
@Table(name = "salary_setting")
public class SalarySetting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 计算提成区间的下限
     */
    private Integer start;

    /**
     * 计算提成区间的上限，没有上限表示xx元以上
     */
    private Integer end;

    /**
     * 提成百分比，这里换算成小数
     */
    private BigDecimal percent;

    /**
     * 附加奖励
     */
    @Column(name = "extra_award")
    private BigDecimal extraAward;

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
     * 获取计算提成区间的下限
     *
     * @return start - 计算提成区间的下限
     */
    public Integer getStart() {
        return start;
    }

    /**
     * 设置计算提成区间的下限
     *
     * @param start 计算提成区间的下限
     */
    public void setStart(Integer start) {
        this.start = start;
    }

    /**
     * 获取计算提成区间的上限，没有上限表示xx元以上
     *
     * @return end - 计算提成区间的上限，没有上限表示xx元以上
     */
    public Integer getEnd() {
        return end;
    }

    /**
     * 设置计算提成区间的上限，没有上限表示xx元以上
     *
     * @param end 计算提成区间的上限，没有上限表示xx元以上
     */
    public void setEnd(Integer end) {
        this.end = end;
    }

    /**
     * 获取提成百分比，这里换算成小数
     *
     * @return percent - 提成百分比，这里换算成小数
     */
    public BigDecimal getPercent() {
        return percent;
    }

    /**
     * 设置提成百分比，这里换算成小数
     *
     * @param percent 提成百分比，这里换算成小数
     */
    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    /**
     * 获取附加奖励
     *
     * @return extra_award - 附加奖励
     */
    public BigDecimal getExtraAward() {
        return extraAward;
    }

    /**
     * 设置附加奖励
     *
     * @param extraAward 附加奖励
     */
    public void setExtraAward(BigDecimal extraAward) {
        this.extraAward = extraAward;
    }

    public String getType() {
        return type;
    }

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