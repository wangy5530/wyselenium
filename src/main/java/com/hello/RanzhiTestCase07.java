package com.hello;

import com.hello.library.CsvUtility;
import com.hello.library.RanzhiCommon;
import com.hello.model.AdminSubMenu;
import com.hello.model.RanzhiUser;
import org.apache.commons.csv.CSVRecord;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Created by Linty on 9/25/2016.
 * 使用模块化和csv方法进行添加用户管理
 */
public class RanzhiTestCase07 {
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
     * 添加用户，使用csv文档添加。
     */
    @Test
    public void testAddUserByCsv() {
        // 读csv文件
        String csvPath = "src/main/resources/user_list.csv";
        // 用CsvUtitily 读取CSV文件的内容
        Iterable<CSVRecord> records = new CsvUtility().readCsvFile(csvPath);

        // 检查records是否为空
        if (records != null) {
            for (CSVRecord record : records) {
                RanzhiUser userToAdd = new RanzhiUser(
                        record.get(0), record.get(1),
                        Integer.parseInt(record.get(3)),
                        Integer.parseInt(record.get(4)),
                        record.get(2).toCharArray()[0],
                        record.get(5),
                        record.get(0) + record.get(6)
                );
                // 添加用户的数据了
                // 第一步 使用管理员登录系统
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
                common.logIn("admin", "admin");
                Assert.assertEquals("登录页面登录跳转失败",
                        this.baseUrl + "/sys/index.html",
                        driver.getCurrentUrl());
                // 第二步 点击齿轮按钮“后台管理”
                common.selectAdminApp();
                Assert.assertEquals("后台管理页面跳转失败",
                        this.baseUrl + "/sys/admin/",
                        driver.getCurrentUrl());
                // 第三步 点击组织（进入iframe）
                common.selectSubMenuForAdminWithFrame(AdminSubMenu.Organization);
                Assert.assertEquals("组织页面跳转失败",
                        this.baseUrl + "/sys/user-admin.html",
                        driver.getCurrentUrl());

                // 第四步 点击添加成员
                driver.findElement(By.xpath("/html/body/div/div/div/div[1]/div/div[2]/a[1]")).click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Assert.assertEquals("添加成员页面跳转失败",
                        this.baseUrl + "/sys/user-create.html",
                        driver.getCurrentUrl());
                // 第五步 添加成员（退出iframe）
                common.addUserAndSwitchToDefault(userToAdd);
                Assert.assertEquals("添加成员保存成功跳转失败",
                        this.baseUrl + "/sys/user-admin.html",
                        driver.getCurrentUrl());

                // 第六步 退出管理员账户
                common.logOut();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Assert.assertEquals("退出管理员页面跳转失败",
                        this.baseUrl + "/sys/user-login.html",
                        driver.getCurrentUrl());
                // 第七步 用新增的账户登录 和 退出
                // 步骤一：打开页面
                common.openWebPage("/");
                Assert.assertEquals("登录页面打开错误",
                        this.baseUrl + "/sys/user-login-L3JhbnpoaS93d3cvc3lzLw==.html",
                        driver.getCurrentUrl());

                // 步骤二：切换语言
                String actualLanguage1 = common.changeChinese();

                Assert.assertEquals("系统语言切换失败", "简体", actualLanguage1);

                common.logIn(userToAdd.getAccount(), userToAdd.getPassword());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
