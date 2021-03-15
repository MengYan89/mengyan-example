package com.mengyan.pcr.service.base;

public abstract class BaseRegularService extends RegularService {


    public BaseRegularService(String regex) {
        super(regex);
    }



    public abstract Object execute(String msg, Long groupId, Long memberId);
}
