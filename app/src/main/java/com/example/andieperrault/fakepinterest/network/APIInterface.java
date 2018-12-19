package com.example.andieperrault.fakepinterest.network;

import com.example.andieperrault.fakepinterest.pojo.DisconnectUser;
import com.example.andieperrault.fakepinterest.pojo.GetUser;
import com.example.andieperrault.fakepinterest.pojo.GetUserByIdRes;
import com.example.andieperrault.fakepinterest.pojo.PinResp;
import com.example.andieperrault.fakepinterest.pojo.RefreshUserToken;
import com.example.andieperrault.fakepinterest.pojo.UserResp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by andieperrault on 13/12/2018.
 */

public interface APIInterface {


        @Headers("Content-Type: application/json")
        @POST("authentification")
        Call<UserResp> createUser(@Body UserResp user);

        @Headers("Content-Type: application/json")
        @POST("authentificationRefresh")
        Call<RefreshUserToken> refreshToken(@Body RefreshUserToken user);

        @Headers("Content-Type: application/json")
        @POST("getPins")
        Call<PinResp> getPins(@Body PinResp pinResp);

        @Headers("Content-Type: application/json")
        @POST("disconnect")
        Call<DisconnectUser> disconnect(@Body DisconnectUser disconnectUser);


        @Headers("Content-Type: application/json")
        @POST("getUserById")
        Call<GetUser> getUserById(@Body GetUser getUser);


}


