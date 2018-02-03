package com.dx.ss.data.rebate.form;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class DataForm {

    private Integer id;

    /**
     * 账户id
     */
    @NotNull(message = "请指定账户")
    private Integer accountId;

    /**
     * 录入日期
     */
    @NotNull(message = "请指定日期")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordDate;

    /**
     * 订单数量
     */
    @NotNull(message = "请输入订单数量")
    private Integer orderNum;

    /**
     * 粉丝数量
     */
    @NotNull(message = "请输入粉丝数量")
    private Integer fansNum;

    /**
     * 成交预估
     */
    @NotNull(message = "请输入成交预估")
    private BigDecimal dealNum;

    /**
     * 结算预估
     */
    @NotNull(message = "请输入结算预估")
    private BigDecimal settleNum;
}
