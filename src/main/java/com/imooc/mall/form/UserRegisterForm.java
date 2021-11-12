package com.imooc.mall.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRegisterForm {//controller接受的数据单独定义一个类
    //@NotBlank 用于String判断空格
    //@NotNull  判断是否为null
    //NotEmpty 用于判断集合
//    @NotNull：不能为null，但可以为empty
//
//    @NotEmpty：不能为null，而且长度必须大于0
//
//    @NotBlank：只能作用在String上，不能为null，而且调用trim()后，长度必须大于0（去了空格长度还大于零）
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;
}
