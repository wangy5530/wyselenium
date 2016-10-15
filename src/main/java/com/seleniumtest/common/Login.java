package com.seleniumtest.common;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.firefox.internal.ProfilesIni;


import java.io.File;

/**
 * Created by Administrator on 2016/10/15.
 */
public class Login {
    private String baseurl = null;
    WebDriver driver = null;

    /**
     * firefox能正常打开就用第一个构造方法，不能打开用第二个构造方法
     */
    public Login(String _baseurl) {
        this.baseurl = "http://" + _baseurl + "/";
        driver = new FirefoxDriver(new ProfilesIni().getProfile("default"));
        System.out.println("初始化baseurl:" + baseurl + "成功");
        open();
    }

    public Login(String _baseurl, String broserurl) {
        this.baseurl = "http://" + _baseurl + "/";
        driver = new FirefoxDriver();
        System.out.println("初始化baseurl:" + baseurl + "成功");
        System.setProperty("WebDriver.firefox.bin", broserurl);
        System.out.println("初始化broserurl:" + broserurl + "成功");
        open();

    }

    public void loginIn(String username, String password) {
        driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/form/div[2]/div[2]/table/tbody/tr[1]/td/input")).clear();
        driver.findElement(By.id("account")).sendKeys(username);
        driver.findElement(By.cssSelector("html.screen-desktop-wide.device-desktop body.m-user-login div.container div#login div#loginForm.panel-body form.form-condensed div.row div.col-xs-8 table.table.table-form tbody tr td input#password.form-control")).clear();
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.id("submit")).click();
        sleep();
    }

    /**
     * 设置登陆页面语言
     */
    public void setLanguage(String language) {
        WebDriver driver = this.driver;
        driver.findElement(By.xpath("//div[@id=\"langs\"]/button")).click();
        sleep();
        switch (language) {
            case "简体":
                driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div/ul/li[1]/a")).click();
                sleep();
                break;
            case "繁体":
                driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div/ul/li[2]/a")).click();
                System.out.println("sucel");
                sleep();
                break;
            case "英语":
                driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div/div/ul/li[3]/a")).click();
                sleep();
                break;
            default:
                System.out.println("尼玛，关键字错误，简体 繁体 英语 三选一，错了剁手");
                break;
        }
    }

    /**
     * 实现前进后退
     *
     * @param jump
     */
    public void jump(String jump) {
        WebDriver driver = this.driver;
        switch (jump) {
            case "下一页":
                driver.navigate().forward();
                sleep();
                break;
            case "上一页":
                driver.navigate().back();
                sleep();
            default:
                System.out.println("fuckyou,尼玛关键字不对，下一页  上一页 二选一，再选错了剁手！");
        }

    }

    public void loginOut() {

    }

    private void open() {
        WebDriver driver = this.driver;
        driver.get(this.baseurl);
        sleep();
    }

    private void close() {
        this.driver.close();
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
