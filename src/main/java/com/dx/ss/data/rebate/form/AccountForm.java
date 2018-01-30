package com.dx.ss.data.rebate.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

@Setter
@Getter
public class AccountForm {

    /**
     * 账户ID
     */
    private Integer id;

    /**
     * 账户名称
     */
    @NotBlank(message = "请输入账户名称")
    private String accountName;

    /**
     * 联盟名称
     */
    @NotBlank(message = "请输入联盟名称")
    private String unionName;

    /**
     * 推广位
     */
    @NotBlank(message = "请输入推广位")
    private String recommend;
}
