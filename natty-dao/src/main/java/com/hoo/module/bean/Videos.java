package com.hoo.module.bean;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Videos {
    private Long vId;

    private String title;

    private Integer type;

    private String resourceType;

    private Double score;

    private String img;

    private String area;

    private Integer showTime;

    private String tabs;

    private Date updateTime;

    private Date creatTime;

    private Integer status;
	
	 private String description;
	
	private String information;
	
	private String introduce;

	private String searchText;
}