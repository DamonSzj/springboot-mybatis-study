package com.spdb;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootMybatisStudyApplicationTests {

    @Before
    public void testBefore() {
        System.out.println("测试开始...");
    }
    @After
    public void testAfter(){
        System.out.println("测试结束...");
    }
}
