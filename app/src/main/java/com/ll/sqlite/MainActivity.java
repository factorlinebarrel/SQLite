package com.ll.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ll.sqlite.db.PersonSQLiteOpenHelper;

public class MainActivity extends AppCompatActivity {
    private PersonSQLiteOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
