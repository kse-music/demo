package com.hiekn.demo.bean.result;

import javax.ws.rs.QueryParam;

public class BaseParam {
    @QueryParam("userId")
    private Integer userId;
    @QueryParam("token")
    private String token;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
