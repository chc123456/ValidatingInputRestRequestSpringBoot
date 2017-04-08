package com.github.alex.validate.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ChenChang on 2017/4/8.
 */
public class UserRequest {
    private Long id;
    @NotNull
    private String username;
    @NotNull
    @Size(min = 6, max = 30)
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
