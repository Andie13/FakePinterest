package com.example.andieperrault.fakepinterest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.andieperrault.fakepinterest.network.APIClient;
import com.example.andieperrault.fakepinterest.network.APIInterface;
import com.example.andieperrault.fakepinterest.pojo.UserResp;
import com.example.andieperrault.fakepinterest.secu.MD5HashPass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.andieperrault.fakepinterest.utils.Const.WSTOKEN;
import static com.example.andieperrault.fakepinterest.utils.Const.LOGIN;
import static com.example.andieperrault.fakepinterest.utils.Const.PREF;
import static com.example.andieperrault.fakepinterest.utils.Const.PSWD;
import static com.example.andieperrault.fakepinterest.utils.Const.WSTOKENREFRESH;

public class AccueilActivity extends AppCompatActivity {
    APIInterface apiInterface;
    EditText editLogin;
    EditText editPswd;
    Button connectButton;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);
        this.editLogin = findViewById(R.id.login);
        this.editPswd = findViewById(R.id.password);
        this.connectButton = findViewById(R.id.connectButton);

        preferences = this.getSharedPreferences(PREF, Context.MODE_PRIVATE);





    }

    public void authenticate(View view) {
        apiInterface = APIClient.getClient().create(APIInterface.class);


        String loginInput = editLogin.getText().toString();
        String pswdInput = editPswd.getText().toString();



        //HASH pass input
        MD5HashPass md5HashPass = new MD5HashPass();
        String cypherPass = md5HashPass.getHashedPass(pswdInput);
        Log.i("PSW", cypherPass);

        /**
         Create new user
         **/
        UserResp user = new UserResp(loginInput, cypherPass);
        Call<UserResp> call1 = apiInterface.createUser(user);
        call1.enqueue(new Callback<UserResp>() {
            @Override
            public void onResponse(Call<UserResp> call, Response<UserResp> response) {
                if (response.isSuccessful()) {

                    Log.i("**** resp", response.body().getResult().getWsToken());
                    preferences.edit().putString(WSTOKEN,response.body().getResult().getWsToken()).apply();
                    preferences.edit().putString(WSTOKENREFRESH,response.body().getResult().getRefreshToken()).apply();
                    Log.i("**** PREF", preferences.getString(WSTOKEN,""));
                    Log.i("**** PREF", preferences.getString(WSTOKENREFRESH,""));
                    Intent intent = new Intent(AccueilActivity.this, PinDisplayActivity.class);
                    startActivity(intent);

                }
            }

            @Override
            public void onFailure(Call<UserResp> call, Throwable t) {
                call.cancel();
            }
        });
    }
}



