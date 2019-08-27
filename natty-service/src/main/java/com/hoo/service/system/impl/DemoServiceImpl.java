package com.hoo.service.system.impl;
import com.hoo.service.system.DemoService;
import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public String test() {
        return "Hello World!";
    }
}
