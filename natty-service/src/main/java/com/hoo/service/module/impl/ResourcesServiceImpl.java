package com.hoo.service.module.impl;

import com.hoo.module.bean.Resources;
import com.hoo.module.dao.ResourcesMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoo.service.module.ResourcesService;
import org.springframework.stereotype.Service;

@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

}
