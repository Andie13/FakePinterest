package com.example.andieperrault.fakepinterest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.andieperrault.fakepinterest.network.APIClient;
import com.example.andieperrault.fakepinterest.network.APIInterface;
import com.example.andieperrault.fakepinterest.pojo.GetUser;
import com.example.andieperrault.fakepinterest.pojo.PinResp;
import com.example.andieperrault.fakepinterest.pojo.UserResp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

import static com.example.andieperrault.fakepinterest.utils.Const.PREF;
import static com.example.andieperrault.fakepinterest.utils.Const.WSTOKEN;
import static com.example.andieperrault.fakepinterest.utils.Const.WSTOKENREFRESH;

public class ShowUserDetailsActivity extends AppCompatActivity {

    ImageView pic;
    TextView firstname;
    TextView lastName;



    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_details);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        pic = findViewById(R.id.picture);
        firstname =(TextView) findViewById(R.id.firstName);
        lastName =(TextView) findViewById(R.id.lastName);

        Intent intent = getIntent();
        Log.i("USEERID", intent.getStringExtra("userId"));
        SharedPreferences preferences = getSharedPreferences(PREF, MODE_PRIVATE);
        String wsToken = preferences.getString(WSTOKEN,"");
        int id = Integer.parseInt(intent.getStringExtra("userId"));

        getUserDetails(id,wsToken);

    }

    public void getUserDetails(int id, String wsToken){
        GetUser user = new GetUser(id, wsToken);
        Call<GetUser> call1 = apiInterface.getUserById(user);
        call1.enqueue(new Callback<GetUser>() {
            @Override
            public void onResponse(Call<GetUser> call, Response<GetUser> response) {
                if (response.isSuccessful()) {

                  Log.i("SUCCESS", "OK");

                    Glide.with(ShowUserDetailsActivity.this.getApplicationContext()).load(response.body().getResult().getPicture()).apply(RequestOptions.centerCropTransform()).into(pic);
                    firstname.setText(response.body().getResult().getFirstName());
                    lastName.setText(response.body().getResult().getLastName());

                }
            }

            @Override
            public void onFailure(Call<GetUser> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
