package com.github.alex.validate.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by ChenChang on 2017/4/8.
 */
public class UserRequest {
    private Long id;
    @NotNull
    @Size(min = 3)
    private String username;
    @NotNull
    @Size(min = 6, max = 30,message = "密码必须为6-30位之间")
    private String password;
    @Email
    @NotNull
    private String email;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
