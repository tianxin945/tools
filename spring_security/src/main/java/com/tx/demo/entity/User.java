package com.tx.demo.entity;

import lombok.Data;

/**
 * @Author tianxin
 * @Date 2019/1/30
 * @Description:
 */
@Data
public class User {
    private String userName;
    private String passWord;

    public User() {
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}
