package com.hiekn.demo.bean.result;

import javax.ws.rs.QueryParam;

public class BaseParam {
    @QueryParam("userId")
    private Integer userId;
    @QueryParam("token")
    private String token;
    @QueryParam("tt")
    private Long tt;

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

    public Long getTt() {
        return tt;
    }

    public void setTt(Long tt) {
        this.tt = tt;
    }
}
