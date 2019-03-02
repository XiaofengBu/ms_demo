package com.xiaofeng.ms.util;


import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {
    private static String md5(String src){
        return DigestUtils.md5Hex(src);
    }
    private static final String salt = "sdj42fd";
    public static String inputPass2TranPass(String inputPass){
        String str = salt + inputPass;
        return md5(str);
    }

    public static String tranPass2DBPass(String tranPass, String salt){
        String str = salt + tranPass;
        return md5(str);
    }
    public static String inputPass2DBPass(String inputPass,String salt){
        String tranPass = inputPass2TranPass(inputPass);
        String dbPass = tranPass2DBPass(tranPass,salt);
        return dbPass;
    }
}
