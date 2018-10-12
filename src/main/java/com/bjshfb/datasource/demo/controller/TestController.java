package com.bjshfb.datasource.demo.controller;

import com.bjshfb.datasource.demo.datasource.DBIdentifier;
import com.bjshfb.datasource.demo.service.PrjService;
import com.bjshfb.datasource.demo.service.UserService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: datasource
 * @description:
 * @author: fuzq
 * @create: 2018-10-12 09:45
 **/
@RestController
@RequestMapping(value = "/test")
public class TestController {
    @Autowired
    private PrjService prjService;

    @Autowired
    private UserService userService;

    /**
     * @Description: //TODO 测试连接pg库
     * @Param: [request, response, projectCode]
     * @return: java.lang.String
     * @Author: fuzq
     * @Date: 2018/10/12 15:12
     **/
    @RequestMapping(value = "/ttt", method = RequestMethod.GET)
    public String get(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(value="projectCode", required=true) String projectCode) {
        DBIdentifier.setProjectCode(projectCode);
        prjService.get();
        return "test";
    }

    /**
     * @Description: //TODO 测试连接mysql库
     * @Param: [request, response, projectCode]
     * @return: java.lang.String
     * @Author: fuzq
     * @Date: 2018/10/12 15:12
     **/
    @RequestMapping(value = "/zzz", method = RequestMethod.GET)
    public String getMysql(HttpServletRequest request, HttpServletResponse response,
                      @RequestParam(value="projectCode", required=true) String projectCode) {

        DBIdentifier.setProjectCode(projectCode);
        userService.getUser();
        return "zzz";
    }
}