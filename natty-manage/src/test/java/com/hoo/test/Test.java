package com.hoo.test;

import com.hoo.module.bean.Resources;
import com.hoo.module.dao.ResourcesMapper;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Resource
    private ResourcesMapper resourcesMapper;

    @org.junit.Test
    public void testList(){
        List<Resources> resources = resourcesMapper.selectList(null);
        resources.forEach(item -> {
            System.out.println(item);
        });
    }
}
