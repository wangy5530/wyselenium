package com.hello;

import com.hello.library.DbUtility;
import com.hello.library.RanzhiCommon;
import com.hello.model.AdminSubMenu;
import com.hello.model.RanzhiUser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Linty on 9/25/2016.
 * 数据库读取数据进行数据驱动和模块化
 */
public class RanzhiTestCase08 {
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
     * 添加用户，首先运行userList.sql
     */
    @Test
    public void testAddUserByDb() {
        // 创建连接字符串，并获取数据库连接
        // jdbc java数据库驱动
        // mysql 驱动mysql
        // localhost mysql的主机
        // test 数据库名
        // user password 用户名 密码
        String connectionString = "jdbc:mysql://localhost/test?user=root&password=";

        String sql = "SELECT \n" +
                "  `account`,\n" +
                "  `realname`,\n" +
                "  `gender`,\n" +
                "  `dept`,\n" +
                "  `role`,\n" +
                "  `password`,\n" +
                "  `email` \n" +
                "FROM\n" +
                "  `test`.`userlist` \n" +
                "LIMIT 0, 1000 ;";

        // 实例化一个DbUtility类的对象，调用该对象的getResultSetForMySQL方法
        ResultSet rs = new DbUtility().getResultSetForMySQL(connectionString, sql);

        // 开始做测试
        if (rs != null) {
            try {
                do {
                    RanzhiUser userToAdd = new RanzhiUser(
                            rs.getString("account"),
                            rs.getString("realname"),
                            Integer.parseInt(rs.getString("role")),
                            Integer.parseInt(rs.getString("dept")),
                            rs.getString("gender").toCharArray()[0],
                            rs.getString("password"),
                            rs.getString("account") + rs.getString("email")
                    );// 添加用户的数据了
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
                        Thread.sleep(2000);
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
