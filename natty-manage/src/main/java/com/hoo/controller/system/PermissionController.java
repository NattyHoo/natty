package com.hoo.controller.system;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hoo.bean.Result;
import com.hoo.service.system.PermissionService;
import com.hoo.system.bean.Permission;
import com.hoo.system.model.Menus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hushuai
 * @since 2019-08-25
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @PostMapping("menu")
    public Result menu(){


        List<Permission> pid = permissionService.list(new QueryWrapper<Permission>().eq("pid", 0));
        List<Menus> parentMenus = new ArrayList<>();
        pid.forEach(item -> {
            Menus menus = new Menus();
            menus.setName(item.getName());
            menus.setPath(item.getPage());
            menus.setIconCls(StringUtils.isBlank(item.getIcon()) ? "fa fa-user-o fa-fw" : item.getIcon());
            List<Permission> childs = permissionService.list(new QueryWrapper<Permission>().eq("pid", item.getId()));
            List<Menus> children = new ArrayList<>();
            childs.forEach(child ->{
                Menus menu = new Menus();
                menu.setName(child.getName());
                menu.setPath(child.getPage());
                menu.setIconCls(StringUtils.isBlank(child.getIcon()) ? "fa fa-user-o fa-fw" : child.getIcon());
                children.add(menu);
            });
            menus.setChildren(children);
            parentMenus.add(menus);
        });
        return Result.success(parentMenus);
    }
}

