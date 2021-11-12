package com.imooc.mall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)//让测试在Spring容器环境下执行。如测试类中无此注解，将导致service,dao等自动注入失败。
@SpringBootTest//测试类需要，空指针也可运行,运行tomcat
public class MallApplicationTests {

    @Test
    public void load(){
        System.out.println("hello git");
        System.out.println("hello git2");
    }

}

