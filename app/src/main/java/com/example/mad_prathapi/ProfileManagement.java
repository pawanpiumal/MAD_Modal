package com.example.mad_prathapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mad_prathapi.Database.DBHandler;

import java.util.List;

public class ProfileManagement extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextDateOfBirth;
    EditText editTextPassword;
    RadioButton Male;
    RadioButton Female;


    Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);


         editTextUserName = findViewById(R.id.etUserName);
         editTextDateOfBirth = findViewById(R.id.etDateOfBirth);
         editTextPassword = findViewById(R.id.etPassword);
         Male = findViewById(R.id.radioMale);
         Female = findViewById(R.id.radioFemale);
         btnUpdate = findViewById(R.id.btnUpdate);


         btnUpdate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 DBHandler dbHandler1 = new DBHandler(ProfileManagement.this);
                 String Username = editTextUserName.getText().toString().trim();
                 String password = editTextPassword.getText().toString().trim();
                 String date = editTextDateOfBirth.getText().toString().trim();
                 boolean male = Male.isChecked();
                 boolean female = Female.isChecked();
                 String gender = null;
                 if(male){
                     gender = "MALE";
                 }
                 if(female){
                     gender = "FEMALE";
                 }

                 if(!TextUtils.isEmpty(Username)||!TextUtils.isEmpty(date)||!TextUtils.isEmpty(gender)) {
                     if(dbHandler1.addInfo(Username, date, gender)){
                         Toast.makeText(ProfileManagement.this,"Added",Toast.LENGTH_SHORT).show();
                         Intent intent = new Intent(ProfileManagement.this, EditProfile.class);
                         startActivity(intent);

                     }else{
                         Toast.makeText(ProfileManagement.this,"Not Added",Toast.LENGTH_SHORT).show();
                     }



                 }else{

                     Toast.makeText(ProfileManagement.this,"Fill the fields",Toast.LENGTH_SHORT).show();

                 }



             }
         });

    }
}
