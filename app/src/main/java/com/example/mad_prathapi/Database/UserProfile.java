package com.example.mad_prathapi.Database;

import android.provider.BaseColumns;

public class UserProfile {

    private UserProfile(){}

    protected static class Users implements BaseColumns{


        protected static final String TABLE_NAME="userinfo";
        protected static final String COLUMN_NAME_USERNAME = "username";
        protected static final String COLUMN_NAME_DATEOFBIRTH= "dateofbirth";
        protected static final String COLUMN_NAME_GENDER= "gender";



    }

}
