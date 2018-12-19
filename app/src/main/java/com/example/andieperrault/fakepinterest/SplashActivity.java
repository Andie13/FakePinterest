package com.example.andieperrault.fakepinterest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;

import com.example.andieperrault.fakepinterest.network.APIClient;
import com.example.andieperrault.fakepinterest.network.APIInterface;
import com.example.andieperrault.fakepinterest.pojo.RefreshTokenId;
import com.example.andieperrault.fakepinterest.pojo.RefreshUserToken;
import com.example.andieperrault.fakepinterest.pojo.UserResp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.andieperrault.fakepinterest.utils.Const.PREF;
import static com.example.andieperrault.fakepinterest.utils.Const.WSTOKEN;
import static com.example.andieperrault.fakepinterest.utils.Const.WSTOKENREFRESH;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class SplashActivity extends AppCompatActivity {


    /**
     * Duration of wait
     **/
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    APIInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fullscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences(PREF, MODE_PRIVATE);
                //test
                //preferences.edit().putString(WSTOKENREFRESH,"").apply();
                if ((preferences.getString(WSTOKENREFRESH, "")).equals("")) {

                    //C'est un nouvel utilisateur
                   goToLoginPage();
                } else {

                    //l'utilisateur s'est déjà connecté. On récupère le tolen pour le rafraîchir.
                    String tok = preferences.getString(WSTOKENREFRESH, "");
                    refreshToken(tok);

                }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }


    public void refreshToken(String tok) {

        apiInterface = APIClient.getClient().create(APIInterface.class);
        /**
         Create new user
         **/
        RefreshUserToken userToken = new RefreshUserToken(tok);
        Call<RefreshUserToken> call1 = apiInterface.refreshToken(userToken);
        call1.enqueue(new Callback<RefreshUserToken>() {
            @Override
            public void onResponse(Call<RefreshUserToken> call, Response<RefreshUserToken> response) {
                if (response.isSuccessful()) {

                    SharedPreferences preferences = getSharedPreferences(PREF, MODE_PRIVATE);
                    preferences.edit().putString(WSTOKEN, response.body().getResult().getWsToken()).apply();
                    preferences.edit().putString(WSTOKENREFRESH, response.body().getResult().getRefreshToken()).apply();

                    Log.i("RESPONSE REFRESH", "Ok");
                    gotoPinDisplay();

                }else{
                    //Le jeton n'est plus valide ou obsolète.
                    goToLoginPage();
                }
            }

            @Override
            public void onFailure(Call<RefreshUserToken> call, Throwable t) {
                call.cancel();
            }
        });


    }
    public void goToLoginPage(){
        Intent mainIntent = new Intent(SplashActivity.this, AccueilActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }

    public void gotoPinDisplay(){
        Intent mainIntent = new Intent(SplashActivity.this, PinDisplayActivity.class);
        SplashActivity.this.startActivity(mainIntent);
        SplashActivity.this.finish();
    }
}
