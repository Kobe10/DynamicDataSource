package com.bjshfb.datasource.demo.datasource;

import java.util.TimerTask;

/**
 * @program: datasource
 * @description: 定时清除数据源
 * @author: fuzq
 * @create: 2018-10-11 15:21
 **/
public class ClearIdleTimerTask extends TimerTask {
    @Override
    public void run() {
        DDSHolder.instance().clearIdleDDS();
    }
}
