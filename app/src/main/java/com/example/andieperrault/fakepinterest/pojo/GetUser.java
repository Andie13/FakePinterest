package com.example.andieperrault.fakepinterest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.GET;

/**
 * Created by andieperrault on 18/12/2018.
 */

public class GetUser {
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
    private GetUserByIdRes result;
    @SerializedName("userId")
    @Expose
    private int userId;
    @SerializedName("wsToken")
    @Expose
    private String wsToken;

    public GetUser(int userId, String wsToken){
        this.userId = userId;
        this.wsToken = wsToken;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int id) {
        this.userId = userId;
    }

    public String getWsToken() {
        return wsToken;
    }

    public void setWsToken(String wsToken) {
        this.wsToken = wsToken;
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

    public GetUserByIdRes getResult() {
        return result;
    }

    public void setResult(GetUserByIdRes result) {
        this.result = result;
    }



}
