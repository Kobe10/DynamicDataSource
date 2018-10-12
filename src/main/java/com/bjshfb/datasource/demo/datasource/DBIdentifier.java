package com.bjshfb.datasource.demo.datasource;

/**
 * @program: datasource
 * @description: 通过项目编号区分数据库
 * @author: fuzq
 * @create: 2018-10-11 15:03
 **/
public class DBIdentifier {
    private static ThreadLocal<String> projectCode = new ThreadLocal<>();

    public static String getProjectCode() {
        return projectCode.get();
    }

    public static void setProjectCode(String code) {
        projectCode.set(code);
    }
}
