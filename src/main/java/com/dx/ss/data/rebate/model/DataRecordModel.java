package com.dx.ss.data.rebate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date recordDate;

    /**
     * 订单数量
     */
    private int orderNum;

    /**
     * 粉丝数量
     */
    private int fansNum;

    /**
     * 成交预估
     */
    private BigDecimal dealNum = new BigDecimal(0);

    /**
     * 结算预估
     */
    private BigDecimal settleNum = new BigDecimal(0);
}
