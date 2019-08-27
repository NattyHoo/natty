package com.hoo.module.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Resources {

    @TableId
    private Long rId;

    private Long vId;

    private Integer type;

    private String typeName;

    private String uri;

    private Date creatTime;

    private Date updateTime;

    private Integer status;
}