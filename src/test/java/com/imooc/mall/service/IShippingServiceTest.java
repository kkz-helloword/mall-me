package com.imooc.mall.service;

import com.imooc.mall.MallApplicationTests;
import com.imooc.mall.enums.ResponseEnum;
import com.imooc.mall.form.ShippingForm;
import com.imooc.mall.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
@Slf4j
public class IShippingServiceTest extends MallApplicationTests {

    @Autowired
    private IShippingService shippingService;
    private  Integer uid = 1;
    private ShippingForm form;
    private Integer shippingId = 6;
    @Before
    public void before(){
        ShippingForm form = new ShippingForm();
        form.setReceiverName("张凯");
        form.setReceiverAddress("杭州电子科技大学");
        form.setReceiverCity("杭州");
        form.setReceiverMobile("15968186529");
        form.setReceiverPhone("66387206");
        form.setReceiverProvince("北京");
        form.setReceiverDistrict("下沙区");
        form.setReceiverZip("473500");
        this.form = form;
    }
    @Test
    public void add() {
        ResponseVo<Map<String, Integer>> responseVo = shippingService.add(uid, form);
        log.info("result={}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }

    @Test
    public void delete() {
        Integer shippingId = 7;
        ResponseVo responseVo = shippingService.delete(uid, shippingId);
        log.info("result={}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }

    @Test
    public void update() {
        form.setReceiverCity("北京");
        ResponseVo responseVo = shippingService.update(uid, shippingId,form);
        log.info("result={}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }

    @Test
    public void list() {
        ResponseVo responseVo = shippingService.list(uid, 1,10);//第一页十条
        log.info("result={}",responseVo);
        Assert.assertEquals(ResponseEnum.SUCCESS.getCode(),responseVo.getStatus());
    }
}