package com.dinesh.dineshwayaman.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dinesh.dineshwayaman.Models.LoginResponse;
import com.dinesh.dineshwayaman.Models.UserData;
import com.dinesh.dineshwayaman.R;
import com.dinesh.dineshwayaman.Services.ApiServices;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText edtName, edtPassword;
    private AppCompatButton btnLogin;


    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();

        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);

        btnLogin.setOnClickListener(v -> {

            String name =  edtName.getText().toString();
            String password = edtPassword.getText().toString();

//            check input fields are empty
            if(name.length() == 0 || password.length() == 0){
                Toast.makeText(this, "Username and password are mandatory", Toast.LENGTH_SHORT).show();
            }else{
                signIn(name, password);
            }
        });



    }

    private void signIn(String name, String password) {


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dineshwayaman.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        ApiServices loginApi = retrofit.create(ApiServices.class);

        Call<LoginResponse> loginResponseCall = loginApi.login(name, password);


        loginResponseCall.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();



                if(response.isSuccessful()){
                    if (loginResponse.getRes_code() == 0){
                        UserData userData = loginResponse.getUser_data();

//                        if login success save response data into shared preference for future use

                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putInt("id", userData.getId());
                        editor.putString("name", userData.getName());
                        editor.putString("email", userData.getEmail());
                        editor.putString("gender", userData.getGender());
                        editor.putString("dob", userData.getDob());
                        editor.putString("company", userData.getCompany());
                        editor.putString("position", userData.getPosition());
                        editor.apply();

                        Toast.makeText(LoginActivity.this, userData.getDob(), Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(i);



                    }else{
                        Toast.makeText(LoginActivity.this, "User Name or Password Incorrect", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

                Toast.makeText(LoginActivity.this, t.toString(), Toast.LENGTH_SHORT).show();



            }
        });

    }

    public void initialize(){
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);


    }

}