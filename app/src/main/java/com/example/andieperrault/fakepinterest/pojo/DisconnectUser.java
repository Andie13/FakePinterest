package com.example.andieperrault.fakepinterest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by andieperrault on 18/12/2018.
 */

public class DisconnectUser {

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
    private Boolean result;

    @SerializedName("wsToken")
    @Expose
    private String wsToken;

    public DisconnectUser(String wsToken){
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

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }


}
