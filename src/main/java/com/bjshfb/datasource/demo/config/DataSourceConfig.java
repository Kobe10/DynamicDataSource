package com.bjshfb.datasource.demo.config;

import com.bjshfb.datasource.demo.datasource.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @program: datasource
 * @description: 数据源配置管理
 * @author: fuzq
 * @create: 2018-10-11 14:54
 **/
@Configuration
public class DataSourceConfig {
    /**
     * @Description: //TODO 根据配置参数创建数据源，使用派生的子类
     * @Param: []
     * @return: javax.sql.DataSource
     * @Author: fuzq
     * @Date: 2018/10/11 15:01
     **/
    @Bean(name = "dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource getDataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.type(DynamicDataSource.class);
        return builder.build();
    }

    /**
     * @Description: //TODO 创建会话工厂
     * @Param: [dataSource]
     * @return: org.apache.ibatis.session.SqlSessionFactory
     * @Author: fuzq
     * @Date: 2018/10/11 15:02
     **/
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        try {
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
