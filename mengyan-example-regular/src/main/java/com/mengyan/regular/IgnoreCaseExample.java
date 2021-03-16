package com.mengyan.regular;

import java.util.regex.Pattern;

/**
 * 正则表达式判断忽略大小写
 */
public class IgnoreCaseExample {

    public static void main(String[] arg) {
        // 忽略SL大小写
        String regex = "(?i)SL";
        // 仅忽略S的大小写
        // String regex = "((?i)S)L";
        String str = "sl";

        System.out.println(Pattern.matches(regex, str));

    }
}
