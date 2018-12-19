package com.example.andieperrault.fakepinterest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.andieperrault.fakepinterest.network.APIInterface;
import com.example.andieperrault.fakepinterest.pojo.GetUser;
import com.example.andieperrault.fakepinterest.pojo.PinResp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.andieperrault.fakepinterest.utils.Const.PREF;
import static com.example.andieperrault.fakepinterest.utils.Const.WSTOKEN;

public class ShowUserDetailsActivity extends AppCompatActivity {

    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);

        Intent intent = getIntent();
        Log.i("USEERID", intent.getStringExtra("userId"));
        SharedPreferences preferences = getSharedPreferences(PREF, MODE_PRIVATE);
        String wsToken = preferences.getString(WSTOKEN,"");
        int id = Integer.parseInt(intent.getStringExtra("userId"));

        getUserDetails(id,wsToken);
    }

    public void getUserDetails(int id, String wsToken){
        final GetUser user = new GetUser(id,wsToken);
        Call<GetUser> call1 = apiInterface.getUserById(user);
        call1.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                if (response.isSuccessful()) {

                    Log.i("RES", "OK");



                }
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                call.cancel();
            }
        });

    }
}
