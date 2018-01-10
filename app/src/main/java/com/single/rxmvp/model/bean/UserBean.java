package com.single.rxmvp.model.bean;

import com.google.gson.annotations.SerializedName;

/**
 * @author li
 *         Create on 2018/1/10.
 * @Description
 *      用户实体类
 */

public class UserBean {
    //token string 用户登录生成的token
    //uid   string 用户Id
    @SerializedName("token")
    private String token;
    @SerializedName("uid")
    private String uid;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
