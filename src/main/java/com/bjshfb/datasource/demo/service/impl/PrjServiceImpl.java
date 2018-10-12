package com.bjshfb.datasource.demo.service.impl;

import com.bjshfb.datasource.demo.service.PrjService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * @program: datasource
 * @description:
 * @author: fuzq
 * @create: 2018-10-12 09:37
 **/
@Service
public class PrjServiceImpl implements PrjService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void get() {
        jdbcTemplate.execute("select * from prj_info");
    }
}
