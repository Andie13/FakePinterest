package com.example.andieperrault.fakepinterest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.andieperrault.fakepinterest.network.APIClient;
import com.example.andieperrault.fakepinterest.network.APIInterface;
import com.example.andieperrault.fakepinterest.pojo.DisconnectUser;
import com.example.andieperrault.fakepinterest.pojo.PinResp;
import com.example.andieperrault.fakepinterest.pojo.ResultPins;
import com.example.andieperrault.fakepinterest.pojo.UserResp;
import com.example.andieperrault.fakepinterest.secu.MD5HashPass;
import com.example.andieperrault.fakepinterest.utils.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.andieperrault.fakepinterest.utils.Const.PREF;
import static com.example.andieperrault.fakepinterest.utils.Const.WSTOKEN;
import static com.example.andieperrault.fakepinterest.utils.Const.WSTOKENREFRESH;

public class PinDisplayActivity extends AppCompatActivity  {


    SharedPreferences preferences;
    List<ResultPins> pinList;
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;


    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_display);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        preferences = this.getSharedPreferences(PREF, Context.MODE_PRIVATE);



        pinList = new ArrayList<>();
        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
       /* LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);*/
       int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        recyclerAdapter = new RecyclerAdapter(getApplicationContext(),pinList);
        recyclerView.setAdapter(recyclerAdapter);



        final PinResp pinResp = new PinResp(preferences.getString(WSTOKEN,""),2);
        Call<PinResp>  call1 = apiInterface.getPins(pinResp);
        call1.enqueue(new Callback<PinResp>() {
            @Override
            public void onResponse(Call<PinResp> call, Response<PinResp>response) {
                if (response.isSuccessful()) {

                   Log.i("RES", "OK");

                    pinList= response.body().getResult();
                    Log.d("TAG","Response = "+pinList);
                    recyclerAdapter.setPinList(pinList);

                }
            }

            @Override
            public void onFailure(Call<PinResp> call, Throwable t) {
                call.cancel();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_file,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_disconnect:
                disConnectUser();
                Intent intent = new Intent(PinDisplayActivity.this, AccueilActivity.class);
                return true;
                default:
                    return onOptionsItemSelected(item);
        }


    }
    public void disConnectUser(){

        final DisconnectUser disconnectUser = new DisconnectUser(preferences.getString(WSTOKEN,""));
        Call<DisconnectUser>  call1 = apiInterface.disconnect(disconnectUser);
        call1.enqueue(new Callback<DisconnectUser>() {
            @Override
            public void onResponse(Call<DisconnectUser> call, Response<DisconnectUser>response) {
                if (response.isSuccessful()) {

                    preferences.edit().putString(WSTOKEN,"").apply();
                    preferences.edit().putString(WSTOKENREFRESH,"").apply();
                    goToLoginPage();


                }
            }

            @Override
            public void onFailure(Call<DisconnectUser> call, Throwable t) {
                call.cancel();
            }
        });
    }
    public void goToLoginPage(){
        Intent mainIntent = new Intent(PinDisplayActivity.this, AccueilActivity.class);
        PinDisplayActivity.this.startActivity(mainIntent);

    }
}
