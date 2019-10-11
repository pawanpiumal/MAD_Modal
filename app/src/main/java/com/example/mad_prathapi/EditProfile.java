package com.example.mad_prathapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.mad_prathapi.Database.DBHandler;
import com.example.mad_prathapi.Database.UserProfile;

import java.lang.ref.SoftReference;
import java.util.List;

public class EditProfile extends AppCompatActivity {

    EditText editTextUserName;
    EditText editTextDateOfBirth;
    EditText editTextPassword;
    RadioButton Male;
    RadioButton Female;

    Button btnSearch;
    Button btnEdit;
    Button btnDelete;

    ListView listView;

    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);



         editTextUserName = findViewById(R.id.etUserName);
         editTextDateOfBirth = findViewById(R.id.etDateOfBirth);
         editTextPassword = findViewById(R.id.etPassword);
         Male = findViewById(R.id.radioMale);
         Female = findViewById(R.id.radioFemale);
         btnSearch = findViewById(R.id.btnSearch);
         btnEdit = findViewById(R.id.btnEdit);
         btnDelete = findViewById(R.id.btnDelete);
         listView = findViewById(R.id.listView);


        DBHandler dbHandler = new DBHandler(this);
        try {
            List<String> list = dbHandler.readAllInfo();
            adapter= new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list);
            listView.setAdapter(adapter);
        }catch (Exception e){
            e.printStackTrace();
        }

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler1 = new DBHandler(EditProfile.this);
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
                if(TextUtils.isEmpty(Username)||TextUtils.isEmpty(date)||TextUtils.isEmpty(gender)) {
                    Toast.makeText(EditProfile.this,"Fill the fields",Toast.LENGTH_SHORT).show();

                }else{


                    if(dbHandler1.updateInfo(Username, date, gender)){
                        Toast.makeText(EditProfile.this,"Updated",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(EditProfile.this,"Not Updated",Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler1 = new DBHandler(EditProfile.this);
                String Username = editTextUserName.getText().toString().trim();
                if(!TextUtils.isEmpty(Username)) {
                    dbHandler1.deleteInfo(Username);
                    adapter.remove(Username);
                    adapter.notifyDataSetChanged();
                }else{

                    Toast.makeText(EditProfile.this,"Fill the fields",Toast.LENGTH_SHORT).show();

                }

            }
        });




        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBHandler dbHandler1 = new DBHandler(EditProfile.this);
                String Username = editTextUserName.getText().toString().trim();

                if(!TextUtils.isEmpty(Username)) {
                   ContentValues values= dbHandler1.readinfo(Username);

                    String username = "";
                    String dob = "";
                    String gender="";

                     username = values.getAsString("u");
                     dob  = values.getAsString("d");
                     gender   = values.getAsString("g");


                   editTextUserName.setText(username);
                   editTextDateOfBirth.setText(dob);
                   if(TextUtils.equals(gender, "MALE")) {
                       Male.setChecked(true);
                   }else{
                       Female.setChecked(true);
                   }

                }else{

                    Toast.makeText(EditProfile.this,"Fill the fields",Toast.LENGTH_SHORT).show();

                }

            }
        });



    }
}
