package com.hoo.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hoo.bean.Result;
import com.hoo.service.system.ManagerRoleService;
import com.hoo.service.system.ManagerService;
import com.hoo.system.bean.Manager;
import com.hoo.system.bean.ManagerRole;
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
@RequestMapping("/managerRole")
public class ManagerRoleController {

    @Resource
    private ManagerRoleService managerRoleService;

    @PostMapping("list")
    public Result list(Integer current,Integer size){
        QueryWrapper<ManagerRole> wrapper = new QueryWrapper<>();
        IPage<ManagerRole> page = managerRoleService.page(new Page<ManagerRole>(current, size), wrapper);
        return Result.success(page);
    }
}

