package com.dx.ss.data.rebate.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class DataRecordModel extends UserAccountModel{

    /**
     * 记录ID
     */
    private Integer id;

    /**
     * 记录日期
     */
    private Date recordDate;

    /**
     * 订单数量
     */
    private Integer orderNum;

    /**
     * 粉丝数量
     */
    private Integer fansNum;

    /**
     * 成交预估
     */
    private BigDecimal dealNum;

    /**
     * 结算预估
     */
    private BigDecimal settleNum;
}
