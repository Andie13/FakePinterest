package com.example.andieperrault.fakepinterest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.xml.transform.Result;

/**
 * Created by andieperrault on 13/12/2018.
 */

public class UserResp {

    @SerializedName("login")
    public String login;
    @SerializedName("password")
    public String password;
    @SerializedName("wsToken")
    @Expose
    public String wsToken;
    @SerializedName("refreshToken")
    @Expose
    public String refreshToken;
    @SerializedName("error")
    @Expose
    private Object error;
    @SerializedName("error_code")
    @Expose
    private Integer errorCode;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("result")
    @Expose
    private ResultAuth result;

    //constructeur pour  1ere authent
    public UserResp(String login, String password) {
        this.login = login;
        this.password = password;
    }
    //constructeur pour  1ere authent
    public UserResp(String refreshToken) {
        this.refreshToken = refreshToken;
    }


    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public ResultAuth getResult() {
        return result;
    }

    public void setResult(ResultAuth result) {
        this.result = result;
    }

}
