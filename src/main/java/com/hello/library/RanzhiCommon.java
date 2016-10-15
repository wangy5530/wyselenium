package com.hello.library;

import com.hello.model.AdminSubMenu;
import com.hello.model.RanzhiUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 * Created by Linty on 9/11/2016.
 * 模块化然之的基本功能和业务逻辑
 */
public class RanzhiCommon {
    private WebDriver driver;
    private String baseUrl;

    // 构造方法

    /**
     * 构造方法，在实例化该类的时候，必须传递WebDriver和BaseURl进来
     *
     * @param driver：需要进行操作的浏览器驱动
     * @param baseUrl：需要进行操作的页面地址
     */
    public RanzhiCommon(WebDriver driver, String baseUrl) {
        this.driver = driver;
        this.baseUrl = baseUrl;
    }

    /**
     * 实际上的登录方法
     *
     * @param account：用户名
     * @param password：密码
     */
    public void logIn(String account, String password) {
        WebDriver driver = this.driver;
        // 输入用户名
        WebElement accountElement = driver.findElement(By.id("account"));
        accountElement.clear();
        accountElement.sendKeys(account);

        // 输入密码
        WebElement passwordElement = driver.findElement(By.id("password"));
        passwordElement.clear();
        passwordElement.sendKeys(password);

        // 点击登录按钮
        driver.findElement(By.id("submit")).click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改语言为中文
     *
     * @return 返回T，修改成功。否则失败。。
     */
    public String changeChinese() {
        WebDriver driver = this.driver;
        // 步骤2
        // 点击 切换语言 复制出来的xpath //*[@id="langs"]/button
        // 声明一个常量，来定义 选择语言的按钮的 xpath
        final String LANG_XPATH = "//div[@id=\"langs\"]/button";
        driver.findElement(By.xpath(LANG_XPATH)).click();

        // 点击 简体 复制出来的xpath //*[@id="langs"]/ul/li[1]/a
        // 但是我们可以用 link text
        driver.findElement(By.linkText("简体")).click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return driver.findElement(By.xpath(LANG_XPATH)).getText();
    }

    /**
     * 模块化操作 1 ： 抽取打开页面的方法
     *
     * @param url: 打开页面的url，需要配合baseUrl
     *             baseUrl + url
     */
    public void openWebPage(String url) {
        WebDriver driver = this.driver;

        driver.get(this.baseUrl + url);

        // 让java代码停止运行1秒钟，等待浏览器进一步响应
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 登出系统
     */
    public void logOut() {
        WebDriver driver = this.driver;
        driver.findElement(By.id("start")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.linkText("退出")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    /**
     * 选择后台管理应用
     */
    public void selectAdminApp() {
        WebDriver driver = this.driver;
        driver.findElement(By.xpath("//li[@id=\"s-menu-superadmin\"]/button")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 选择后台管理的子目录
     * 方法默认进入后台管理页面所在的frame
     *
     * @param subMenu 枚举类型的参数。
     */
    public void selectSubMenuForAdminWithFrame(AdminSubMenu subMenu) {
        switch (subMenu) {
            case Organization:
                WebDriver driver = this.driver;
                driver.switchTo().frame("iframe-superadmin");
                driver.findElement(By.xpath("//*[@id=\"mainNavbar\"]/div[2]/ul/li[2]/a")).click();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    /**
     * 真正的添加用户
     *
     * @param userToAdd RanzhiUser 类
     */
    public void addUserAndSwitchToDefault(RanzhiUser userToAdd) {
        WebDriver driver = this.driver;
        WebElement userName = driver.findElement(By.cssSelector("#account"));
        userName.clear();
        userName.sendKeys(userToAdd.getAccount());

        // 输入真实姓名
        WebElement realName = driver.findElement(By.cssSelector("#realname"));
        realName.clear();
        realName.sendKeys(userToAdd.getName());

        // 输入性别
        if (userToAdd.getGender() == 'M') {
            driver.findElement(By.cssSelector("#gender1")).click();
        } else if (userToAdd.getGender() == 'f') {
            driver.findElement(By.cssSelector("#gender2")).click();
        }

        // 输入部门
        WebElement department = driver.findElement(By.cssSelector("#dept"));
        Select departmentSelect = new Select(department);
        departmentSelect.selectByIndex(userToAdd.getDepartment());

        // 输入角色
        WebElement role = driver.findElement(By.cssSelector("#role"));
        Select roleSelect = new Select(role);
        roleSelect.selectByIndex(userToAdd.getRole());

        // 输入密码
        WebElement password1 = driver.findElement(By.cssSelector("#password1"));
        password1.clear();
        password1.sendKeys(userToAdd.getPassword());

        // 输入确认密码
        WebElement password2 = driver.findElement(By.cssSelector("#password2"));
        password2.clear();
        password2.sendKeys(userToAdd.getPassword());

        // 输入邮箱
        WebElement email = driver.findElement(By.cssSelector("#email"));
        email.clear();
        email.sendKeys(userToAdd.getEmail());

        // 提交
        driver.findElement(By.cssSelector("#submit")).click();
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 退出到默认的frame
        driver.switchTo().defaultContent();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
