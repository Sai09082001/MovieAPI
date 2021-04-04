package com.techja.lesson28api.api.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AccountReqModel implements Serializable {
    @SerializedName("username")
    private String userName;
    @SerializedName("password")
    private String password;
    @SerializedName("request_token")
    private String requestToken;

    public AccountReqModel(String userName, String password, String requestToken) {
        this.userName = userName;
        this.password = password;
        this.requestToken = requestToken;
    }




}
