package com.hoo.service.system.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hoo.service.system.ManagerService;
import com.hoo.system.bean.Manager;
import com.hoo.system.dao.ManagerMapper;
import org.springframework.stereotype.Service;

/**
 * @author : natty
 * create at:  2019-08-24  09:59
 * @description:
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {
}
