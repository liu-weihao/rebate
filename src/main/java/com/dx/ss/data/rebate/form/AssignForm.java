package com.dx.ss.data.rebate.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class AssignForm {

    @NotBlank(message = "请指定用户")
    private String userId;

    @NotNull(message = "请指定微信账户")
    private Integer accountId;

    @NotBlank(message = "请选择账户类型")
    private String type;
}
