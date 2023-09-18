package com.dinesh.dineshwayaman.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.dinesh.dineshwayaman.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtName, edtPassword;
    private AppCompatButton btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initialize();


        btnLogin.setOnClickListener(v -> {
            if(edtName.getText().length() == 0 || edtPassword.getText().length() == 0){
                Toast.makeText(this, "Username and password are mandatory", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();

            }
        });



    }

    public void initialize(){
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

}