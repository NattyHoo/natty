package com.hoo.system.bean;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;


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
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 角色名称
     */
    @TableField("role_name")
    private String roleName;
    /**
     * 角色描述
     */
    private String descpt;
    /**
     * 角色编号
     */
    private String code;
    /**
     * 操作用户id
     */
    @TableField("insert_uid")
    private Integer insertUid;
    /**
     * 添加数据时间
     */
    @TableField("insert_time")
    private Date insertTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private Date updateTime;


}
