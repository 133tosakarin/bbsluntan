package com.dc.bbsluntan.common;

/**
 * @author DengChao
 */
public class StringUtils {

    public static boolean isNotNull(String str){
        if("".equals(str) || str == null)
            return false;
        return true;
    }

    public static boolean equal(String a,String b){

        return a.equals(b);
    }
}
