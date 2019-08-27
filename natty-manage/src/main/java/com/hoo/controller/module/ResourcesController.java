package com.hoo.controller.module;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hoo.module.bean.Resources;
import com.hoo.service.module.ResourcesService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("resources")
public class ResourcesController{

    @Resource
    private ResourcesService resourcesService;

    @GetMapping("test")
    public Object test(){
        Resources resources = new Resources();
        resources.setVId(9984L);
        QueryWrapper<Resources> queryWrapper = new QueryWrapper<>();
        //sql in
//        lambdaQueryWrapper.in("r_id",new Long[]{1L,2L});
        //ge()  r_id 大于等于25，第一个参数，用于选择这个查询条件是都有效,
        queryWrapper.ge(true,"r_id", 25);
        //le()  r_id 小于等于32，第一个参数可选，同上，用于选择这个查询条件是都有效,
        queryWrapper.le("r_id",30);

        IPage<Resources> page = resourcesService.page(new Page<Resources>(1,30),queryWrapper);
        return page;
    }
}
