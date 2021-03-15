package com.mengyan.pcr.service;

import com.mengyan.pcr.service.base.BaseRegularService;

public class QuerySLService extends BaseRegularService {
    private static String regex = "(?i)SL?";

    public QuerySLService(String regex) {
        super(regex);
    }

    public QuerySLService() {
        super(regex);
    }

    @Override
    public Object execute(String msg, Long groupId, Long memberId) {
        Boolean isSL = SLStorage.isSL(groupId, memberId);
        if (isSL) {
            // TODO 已经进行过SL
        } else {
            // TODO 还没有进行SL

        }
        return null;
    }
}
