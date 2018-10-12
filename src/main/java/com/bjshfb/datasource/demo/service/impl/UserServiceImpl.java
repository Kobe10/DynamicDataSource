package com.bjshfb.datasource.demo.service.impl;

import com.bjshfb.datasource.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @program: datasource
 * @description: 测试连接mysql库
 * @author: fuzq
 * @create: 2018-10-12 13:59
 **/
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void getUser() {
        jdbcTemplate.execute("select * FROM us_user");
    }
}
