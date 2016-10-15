package com.hello.model;

import java.io.Serializable;

/**
 * Created by Linty on 8/18/2016.
 * Ranzhi User Entity Class
 */
public class RanzhiUser implements Serializable {
    private String account;
    private String name;
    private int role;
    private int department;
    private char gender;
    private String password;
    private String email;

    public RanzhiUser() {
    }

    public RanzhiUser(String account, String name,
                      int role, int department, char gender,
                      String password, String email) {
        this.account = account;
        this.name = name;
        this.role = role;
        this.department = department;
        this.gender = gender;
        this.password = password;
        this.email = email;
    }


    public String getAccount() {
        return this.account;
    }

    /**
     * @param account
     */
    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return this.role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getDepartment() {
        return this.department;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public char getGender() {
        return this.gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
