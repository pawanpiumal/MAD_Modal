package com.example.mad_prathapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    EditText editTextUserName;
    EditText editTextPassword;
    Button BtnLogin;
    Button BtnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         editTextUserName = findViewById(R.id.etUserName);
         editTextPassword = findViewById(R.id.etPassword);
         BtnLogin = findViewById(R.id.btnLogin);



         BtnRegister = findViewById(R.id.btnRegiseter);


         BtnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                     Intent intent = new Intent(MainActivity.this, ProfileManagement.class);


                     startActivity(intent);

             }
         });

    }
}
