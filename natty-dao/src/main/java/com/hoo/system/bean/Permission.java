package com.hoo.system.bean;


import java.io.Serializable;
import java.util.Date;
import java.util.List;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author hushuai
 * @since 2019-08-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 父菜单id
     */
    private Integer pid;
    /**
     * 菜单排序
     */
    private Integer zindex;
    /**
     * 权限分类（0 菜单；1 功能）
     */
    private Integer istype;
    /**
     * 描述
     */
    private String descpt;
    /**
     * 菜单编号
     */
    private String code;
    /**
     * 菜单图标名称
     */
    private String icon;
    /**
     * 菜单url
     */
    private String page;
    /**
     * 添加时间
     */
    @TableField("insert_time")
    private Date insertTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}
