package com.dx.ss.data.rebate.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Setter
@Getter
public class SalaryForm {

    private Integer id;

    /**
     * 计算提成区间的下限
     */
    private Integer start = 0;

    /**
     * 计算提成区间的上限，没有上限表示xx元以上
     */
    private Integer end;

    /**
     * 提成百分比，这里换算成小数
     */
    @NotNull(message = "请输入提成比例")
    private BigDecimal percent;

    /**
     * 附加奖励
     */
    @NotNull(message = "请输入额外奖励")
    private BigDecimal extraAward;

    @NotBlank(message = "请选择账号类型")
    private String type;

}
