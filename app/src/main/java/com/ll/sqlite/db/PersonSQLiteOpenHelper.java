package com.ll.sqlite.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * describe
 * Created by LiLei on 2017/7/17.Go.
 * 数据库帮助类,用于创建和管理数据库
 */
public class PersonSQLiteOpenHelper extends SQLiteOpenHelper {
    /**
     * 数据库的构造函数
     * 数据库名称,游标工程,版本号,不可以小于1..
     */
    public PersonSQLiteOpenHelper(Context context) {
        super(context, "user.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table person(_id integer primary key,name varchar(20),age integer);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
