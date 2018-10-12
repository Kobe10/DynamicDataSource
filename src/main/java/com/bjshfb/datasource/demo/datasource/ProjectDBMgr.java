package com.bjshfb.datasource.demo.datasource;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: datasource
 * @description: 项目数据库管理。提供根据项目编码查询数据库名称和IP的接口。
 * @author: fuzq
 * @create: 2018-10-11 15:55
 **/
public class ProjectDBMgr {
    /**
     * 保存项目编码与数据名称的映射关系。这里是硬编码，实际开发中这个关系数据可以保存到redis缓存中；
     * 新增一个项目或者删除一个项目只需要更新缓存。到时这个类的接口只需要修改为从缓存拿数据。
     */
    private Map<String, String> dbNameMap = new HashMap<String, String>();
    /**
     * 保存项目编码与数据库IP的映射关系。
     */
    private Map<String, String> dbIPMap = new HashMap<String, String>();

    /**
     * 保存项目编码与数据库端口的映射关系。
     */
    private Map<String, String> dbPortMap = new HashMap<String, String>();

    /**
     * 保存项目编码与数据库账号的映射关系。
     */
    private Map<String, String> dbUserMap = new HashMap<String, String>();

    /**
     * 保存项目编码与数据库密码的映射关系。
     */
    private Map<String, String> dbPwdMap = new HashMap<String, String>();

    /**
     * 保存项目编码与数据库类型的映射关系。
     */
    private Map<String, String> dbTypeMap = new HashMap<String, String>();

    private ProjectDBMgr() {
        //存储数据库名称
        dbNameMap.put("project_001", "eview");
        dbNameMap.put("project_002", "uservice");
        //数据库Ip
        dbIPMap.put("project_001", "101.201.45.26");
        dbIPMap.put("project_002", "101.201.45.26");
        //端口
        dbPortMap.put("project_001", "26354");
        dbPortMap.put("project_002", "26091");
        //账号
        dbUserMap.put("project_001", "eview");
        dbUserMap.put("project_002", "eland");
        //密码
        dbPwdMap.put("project_001", "eview");
        dbPwdMap.put("project_002", "4v#S75h8uhYYAO1d");
        //数据库类型
        dbTypeMap.put("project_001", "postgresql");
        dbTypeMap.put("project_002", "mysql");
    }

    public static ProjectDBMgr instance() {
        return ProjectDBMgrBuilder.instance;
    }

    /**
     * 实际开发中改为从缓存获取
     * */
    public String getDBName(String projectCode) {
        if (dbNameMap.containsKey(projectCode)) {
            return dbNameMap.get(projectCode);
        }
        return "";
    }

    /**
     * 实际开发中改为从缓存中获取
     * */
    public String getDBIP(String projectCode) {
        if (dbIPMap.containsKey(projectCode)) {
            return dbIPMap.get(projectCode);
        }
        return "";
    }

    public String getDBPort(String projectCode) {
        if (dbPortMap.containsKey(projectCode)) {
            return dbPortMap.get(projectCode);
        }
        return "";
    }

    public String getDBUser(String projectCode) {
        if (dbUserMap.containsKey(projectCode)) {
            return dbUserMap.get(projectCode);
        }
        return "";
    }

    public String getDBPwd(String projectCode) {
        if (dbPwdMap.containsKey(projectCode)) {
            return dbPwdMap.get(projectCode);
        }
        return "";
    }

    public String getDBType(String projectCode) {
        if (dbTypeMap.containsKey(projectCode)) {
            return dbTypeMap.get(projectCode);
        }
        return "";
    }

    private static class ProjectDBMgrBuilder {
        private static ProjectDBMgr instance = new ProjectDBMgr();
    }
}
