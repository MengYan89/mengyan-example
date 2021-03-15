package com.mengyan.pcr.service.base;

import java.util.regex.Pattern;

/**
 * 使用正则表达式判断的Service所需要继承的类
 *
 */
public class RegularService {

    private final String regex ;

    public RegularService(String regex) {
        this.regex = regex;
    }

    public boolean regularJudge(String str) {
        return Pattern.matches(regex,str);
    }
}
