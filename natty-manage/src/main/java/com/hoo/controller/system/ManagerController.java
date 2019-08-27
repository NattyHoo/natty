package com.hoo.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hoo.bean.Result;
import com.hoo.service.system.ManagerService;
import com.hoo.system.bean.Manager;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hushuai
 * @since 2019-08-25
 */
@RestController
@RequestMapping("/manager")
public class ManagerController {

    @Resource
    private ManagerService managerService;

    @PostMapping("list")
    public Result list(Integer current,Integer size){
        QueryWrapper<Manager> wrapper = new QueryWrapper<>();
        IPage<Manager> page = managerService.page(new Page<Manager>(current == null ? 1 : current, size == null ? 10 : size), wrapper);
        return Result.success(page);
    }
}

