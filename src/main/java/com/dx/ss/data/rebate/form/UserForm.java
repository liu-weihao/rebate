package com.dx.ss.data.rebate.form;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserForm {

    @NotBlank(message = "请输入登录用户名")
    private String username;

    @NotBlank(message = "请输入员工姓名")
    private String name;

    @NotBlank(message = "请输入手机号码")
    private String phone;

    @NotNull(message = "请输入工龄")
    private Integer workAge;

    @NotBlank(message = "请输入登录密码")
    private String password;

    private Integer userFrom = 30;

    private Integer status = 10;

}
