package com.bjshfb.datasource.demo.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @program: datasource
 * @description: 定义动态数据源的派生类，从基础的DataSource派生，实现动态性
 * @author: fuzq
 * @create: 2018-10-11 15:04
 **/
public class DynamicDataSource extends DruidDataSource {
    private static Logger log = LogManager.getLogger(DynamicDataSource.class);

    /**
     * 改写本方法是为了在请求不同项目的数据时去连接不同的数据库。
     */
    @Override
    public DruidPooledConnection getConnection() {
        String projectCode = DBIdentifier.getProjectCode();
        //1、获取数据源
        DruidDataSource dds = DDSHolder.instance().getDDS(projectCode);
        //2、如果数据源不存在则创建
        if (dds == null) {
            try {
                //创建数据源
                DruidDataSource newDDS = initDDS(projectCode);
                //将数据源存入缓存
                DDSHolder.instance().addDDS(projectCode, newDDS);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                log.error("Init data source fail. projectCode:" + projectCode);
                return null;
            }
        }
        dds = DDSHolder.instance().getDDS(projectCode);
        try {
            return dds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 以当前数据源对象作为模板复制一份
     *
     * @return dds
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    private DruidDataSource initDDS(String projectCode) throws IllegalArgumentException, IllegalAccessException {
        DruidDataSource dds = new DruidDataSource();
        // 2、复制PoolConfiguration的属性
        Properties properties = System.getProperties();
        Field[] pfields = Properties.class.getDeclaredFields();
        for (Field f : pfields) {
            f.setAccessible(true);
            Object value = f.get(this.getConnectProperties());
            try {
                f.set(properties, value);
            } catch (Exception e) {
                log.info("Set value fail. attr name:" + f.getName());
                continue;
            }
        }
        dds.setConnectProperties(properties);
        // 3、设置数据库名称和IP(一般来说，端口和用户名、密码都是统一固定的)  这里还需要设计数据库的驱动(需要改动)
        String urlFormat = this.getUrl();
        //设置连接地址
        String url = String.format(urlFormat, ProjectDBMgr.instance().getDBType(projectCode), ProjectDBMgr.instance().getDBIP(projectCode),
                ProjectDBMgr.instance().getDBPort(projectCode),
                ProjectDBMgr.instance().getDBName(projectCode));
        dds.setUrl(url);
        //设置用户名密码
        dds.setUsername(ProjectDBMgr.instance().getDBUser(projectCode));
        dds.setPassword(ProjectDBMgr.instance().getDBPwd(projectCode));
        dds = setConfig(dds);
        return dds;
    }

    /**
     * @Description: //TODO 设置连接池其他基本信息
     * @Param: [druidDataSource]
     * @return: com.alibaba.druid.pool.DruidDataSource
     * @Author: fuzq
     * @Date: 2018/10/12 15:49
     **/
    private DruidDataSource setConfig(DruidDataSource datasource) {
        //configuration
        datasource.setInitialSize(3);
        datasource.setMinIdle(3);
        datasource.setMaxActive(15);
        datasource.setMaxWait(5000);
        datasource.setTimeBetweenEvictionRunsMillis(90000);
        datasource.setMinEvictableIdleTimeMillis(1800000);
        // 不需要配置validationQuery，如果不配置的情况下会走ping命令，性能更高。
        datasource.setTestWhileIdle(true);
        datasource.setTestOnBorrow(false);
        datasource.setTestOnReturn(false);

        return datasource;
    }
}