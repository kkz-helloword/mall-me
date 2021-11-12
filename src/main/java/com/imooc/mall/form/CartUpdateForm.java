package com.imooc.mall.form;

import lombok.Data;

@Data
public class CartUpdateForm {
    //非必填，无需校验
    private Integer quantity;

    private Boolean selected;

}
