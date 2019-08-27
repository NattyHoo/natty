package com.hoo.system.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class Manager implements Serializable {
    private static final long serialVersionUID = -3096736268081409238L;

    @TableId
    private Integer id;

    private String username;

    private String mobile;

    private String email;

    private String password;

    private Integer insertUid;

    private Date insertTime;

    private Date updateTime;

    private Boolean isDel;

    private Boolean isJob;

    private String mcode;

    private Date sendTime;

    private Integer version;

}