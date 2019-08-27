package com.hoo.system.model;

import lombok.Data;

import java.util.List;

/**
 * @author : natty
 * create at:  2019-08-25  18:32
 * @description: 菜单模型
 */
@Data
public class Menus {

    private String path;
    private String name;
    private String iconCls;
    private List<Menus> children;

    public Menus( String name,String path, String iconCls) {
        this.path = path;
        this.name = name;
        this.iconCls = iconCls;
    }

    public Menus() {
    }
}
