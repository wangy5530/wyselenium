package com.hello;

import com.hello.library.CsvUtility;
import com.hello.library.RanzhiCommon;
import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Linty on 9/11/2016.
 * 数据驱动，需要创建额外的工具类进行csv操作
 */
public class RanzhiTestCase04 {
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
     * 测试登录。使用CSV文件作为数据源
     */
    @Test
    public void testLogInByCsv() {
        // 定义csv文件所在的路径
        String csvPath = "src/main/resources/user_login.csv";
        // 打开csv文件，返回csv里面的记录
        Iterable<CSVRecord> records = new CsvUtility().readCsvFile(csvPath);

        if (records != null) {
            for (CSVRecord loginRecord : records) {
                String accountToLogIn = loginRecord.get(0);
                String passwordToLogIn = loginRecord.get(1);

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
            }
        }
    }
}
