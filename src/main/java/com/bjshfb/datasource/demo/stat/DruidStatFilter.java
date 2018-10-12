package com.bjshfb.datasource.demo.stat;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * @program: datasource
 * @description: 配置监控拦截器 druid监控拦截器
 * @author: fuzq
 * @create: 2018-10-12 15:30
 **/
@WebFilter(filterName = "druidWebStatFilter",
        urlPatterns = "/*",
        initParams = {
                // 忽略资源，过滤不需要监控的后缀
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),
        })
public class DruidStatFilter extends WebStatFilter {

}

