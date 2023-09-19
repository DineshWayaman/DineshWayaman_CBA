package com.dinesh.dineshwayaman.Views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dinesh.dineshwayaman.R;

public class MainActivity extends AppCompatActivity {

    TextView txtID, txtName, txtEmail, txtDOB, txtGender, txtCompany, txtPosition, txtLoggedName;
    AppCompatButton btnClose, btnViewProf;

    SharedPreferences sharedPreferences;
    Dialog dialog;

    int id;
    String name,email, dob, gender, company, position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnViewProf= findViewById(R.id.btnViewProf);

        txtLoggedName = findViewById(R.id.txtLoggedName);

        initializeShareds();

        txtLoggedName.setText("Hello " + name);


        dialog = new Dialog(this);


        btnViewProf.setOnClickListener(v -> {
            showDialogBox();
        });




    }

    public void showDialogBox() {

        dialog.setContentView(R.layout.profile_details);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);

        txtID = dialog.findViewById(R.id.txtID);
        txtName = dialog.findViewById(R.id.txtName);
        txtEmail = dialog.findViewById(R.id.txtEmail);
        txtDOB = dialog.findViewById(R.id.txtDOB);
        txtGender = dialog.findViewById(R.id.txtGender);
        txtCompany = dialog.findViewById(R.id.txtCompany);
        txtPosition = dialog.findViewById(R.id.txtPosition);
        btnClose = dialog.findViewById(R.id.btnClose);

        txtID.setText("ID: " + String.valueOf(id));
        txtName.setText("Name: " +name);
        txtEmail.setText("Email: "+email);
        txtDOB.setText("DOB: "+dob);
        txtGender.setText("Gender: "+gender);
        txtCompany.setText("Company: "+company);
        txtPosition.setText("Position: "+position);

        btnClose.setOnClickListener(v -> {
            dialog.dismiss();
        });

        dialog.show();

    }

    public void initializeShareds() {
        sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
         id = sharedPreferences.getInt("id", 0);
         name = sharedPreferences.getString("name", "");
         email = sharedPreferences.getString("email", "");
         dob = sharedPreferences.getString("dob", "");
         gender = sharedPreferences.getString("gender", "");
         company = sharedPreferences.getString("company", "");
         position = sharedPreferences.getString("position", "");



    }


}