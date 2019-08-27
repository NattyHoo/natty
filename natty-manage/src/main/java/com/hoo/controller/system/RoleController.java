package com.hoo.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hoo.bean.Result;
import com.hoo.service.system.RoleService;
import com.hoo.system.bean.Role;
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
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @PostMapping("/list")
    public Result list(Integer current,Integer size){
        QueryWrapper<Role> wrapper = new QueryWrapper<>();
        IPage<Role> page = roleService.page(new Page<Role>(current == null ? 1 : current, size == null ? 10 : size), wrapper);
        return Result.success(page);
    }
}

