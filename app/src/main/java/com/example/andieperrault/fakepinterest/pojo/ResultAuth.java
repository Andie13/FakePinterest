package com.example.andieperrault.fakepinterest.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by andieperrault on 13/12/2018.
 */

public class ResultAuth {
    @SerializedName("wsToken")
    @Expose
    private String wsToken;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;

    public String getWsToken() {
        return wsToken;
    }

    public void setWsToken(String wsToken) {
        this.wsToken = wsToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
