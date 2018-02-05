package com.dx.ss.data.rebate.form;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
public class UserForm {

    private String userId;

    @NotBlank(message = "请输入登录用户名")
    private String username;

    @NotBlank(message = "请输入员工姓名")
    private String name;

    @NotBlank(message = "请输入手机号码")
    @Pattern(regexp = "(\\+\\d+)?1[34578]\\d{9}$", message = "手机号码格式不正确")
    private String phone;

    @NotNull(message = "请输入工龄")
    private Integer workAge;

    @NotBlank(message = "请输入登录密码")
    private String password;

    @NotNull(message = "请选择用户角色")
    private Integer roleId;

    private Integer userFrom = 30;

    private Integer status = 10;

}
