package com.seleniumtest.common;

/**
 * Created by Administrator on 2016/10/15.
 */
public class Common {
    private Common() {
    }
    private static Common common = new Common();

    public static Common getCommon(){
        return common;
    }


}
