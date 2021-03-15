package com.mengyan.pcr.service;


import com.mengyan.pcr.service.base.BaseRegularService;

import java.util.Date;
import java.util.TimerTask;

/**
 * 记录SL次数
 */
public class SLService extends BaseRegularService {

    private static String regex = "(?i)SL";

    public SLService(String regex) {
        super(regex);
    }

    public SLService() {
        super(regex);
    }

    @Override
    public Object execute(String msg, Long groupId, Long memberId) {
        Boolean isSL = SLStorage.isSLAndRecord(groupId, memberId);
        if (isSL) {
            // TODO 成功记录SL
        } else {
            // TODO 这个人已经SL过了
        }
        return null;
    }


    public static class SLTimerTask extends TimerTask {
        @Override
        public void run() {
            System.out.println("定时任务");
            System.out.println(new Date());
        }
    }



}
