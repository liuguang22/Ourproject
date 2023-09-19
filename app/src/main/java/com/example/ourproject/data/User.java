package com.example.ourproject.data;

public class User {
    private String username;
    private String password;
    // 其他用户信息（如姓名、邮箱等）

    public User(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
    // getter 和 setter 方法

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



}
