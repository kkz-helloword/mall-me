package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserLoginForm {//controller接受的数据单独定义一个类

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
