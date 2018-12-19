package com.example.andieperrault.fakepinterest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andieperrault on 15/12/2018.
 */

public class PinResp {
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
    private List<ResultPins> result = null;

    @SerializedName("wsToken")
    @Expose
    private String userToken;
    @SerializedName("index")
    @Expose
    private int index;
    //constructeur pour  1ere authent
    public PinResp(String userToken, int index) {
        this.userToken = userToken;
        this.index = index;
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

    public List<ResultPins> getResult() {
        return result;
    }

    public void setResult(List<ResultPins> result) {
        this.result = result;
    }


}
