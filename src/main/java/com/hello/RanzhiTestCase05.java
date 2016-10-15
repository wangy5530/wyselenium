package com.hello;

import com.hello.library.DbUtility;
import com.hello.library.RanzhiCommon;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Linty on 9/25/2016.
 */
public class RanzhiTestCase05 {

    private WebDriver driver;
    private String baseUrl;
    private RanzhiCommon common;

    /**
     * 测试用例执行前，预置条件。
     */
    @Before
    public void setUp() {
        this.driver = new FirefoxDriver();
        this.baseUrl = "http://localhost:808/ranzhi/www";
        this.common = new RanzhiCommon(this.driver, this.baseUrl);
    }

    /**
     * 测试用例执行完，然后清场。
     */
    @After
    public void tearDown() {
        this.driver.quit();
        this.common = null;
    }

    /**
     * 从数据库读取用户名和密码，查询数据库:
     * 前提：需要执行 src/main/resources/userLogin.sql
     */
    @Test
    public void testLogInByDb() {
        // 创建连接字符串，并获取数据库连接
        // jdbc java数据库驱动
        // mysql 驱动mysql
        // localhost mysql的主机
        // test 数据库名
        // user password 用户名 密码
        String connectionString = "jdbc:mysql://localhost/test?user=root&password=";

        String sql = "SELECT " +
                "ul.`account`, " +
                "ul.`password` " +
                "FROM " +
                "test.`userLogin` ul " +
                "LIMIT 0, 1000 ;";

        // 实例化一个DbUtility类的对象，调用该对象的getResultSetForMySQL方法
        ResultSet rs = new DbUtility().getResultSetForMySQL(connectionString, sql);

        // 开始做测试
        if (rs != null) {
            try {
                do {
                    String accountToLogIn = rs.getString("account");
                    String passwordToLogIn = rs.getString("password");

                    WebDriver driver = this.driver;
                    RanzhiCommon common = this.common;
                    // 步骤一：打开页面
                    common.openWebPage("/");
                    Assert.assertEquals("登录页面打开错误",
                            this.baseUrl + "/sys/user-login-L3JhbnpoaS93d3cvc3lzLw==.html",
                            driver.getCurrentUrl());

                    // 步骤二：切换语言
                    String actualLanguage = common.changeChinese();

                    Assert.assertEquals("系统语言切换失败", "简体", actualLanguage);

                    // 步骤三：进行登录
                    common.logIn(accountToLogIn, passwordToLogIn);
                    Assert.assertEquals("登录页面登录跳转失败",
                            this.baseUrl + "/sys/index.html",
                            driver.getCurrentUrl());

                    // 步骤四：退出系统
                    common.logOut();
                    Assert.assertEquals("登录页面退出跳转失败",
                            this.baseUrl + "/sys/user-login.html",
                            driver.getCurrentUrl());
                } while (rs.next());
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {

                try {
                    rs.close();
                } catch (SQLException ignored) {
                } // ignore
            }
        }
    }


}
