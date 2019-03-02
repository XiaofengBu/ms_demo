package com.xiaofeng.ms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtil {
    private static Pattern mobilePattern = Pattern.compile("\\d{11}");
    public static boolean isMobile(String s){
        Matcher matcher = mobilePattern.matcher(s);
        return matcher.matches();
    }
}
