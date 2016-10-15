package com.seleniumtest.test;

import com.seleniumtest.common.Login;

/**
 * Created by Administrator on 2016/10/15.
 */
public class t {
    public static void main(String[] args) {
        Login l = new Login("127.0.0.1/ranzhi/www");
        l.setLanguage("繁体");
        l.setLanguage("英语");
        l.setLanguage("啊实打实的");

        l.setLanguage("简体");


    }
}
