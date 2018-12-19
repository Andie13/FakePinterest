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
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("wsToken")
    @Expose
    private String wsToken;

    public GetUser(int id, String wsToken){
        this.id = id;
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
