package com.ll.sqlite.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ll.sqlite.model.Person;
import com.ll.sqlite.db.PersonSQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * describe
 * Created by LiLei on 2017/7/17.Go.
 */
public class PersonDao {
    //数据库帮助类
    private PersonSQLiteOpenHelper helper;

    public PersonDao(Context context) {
        helper = new PersonSQLiteOpenHelper(context);
    }

    public void insert(Person person) {
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("insert into person(name,age) values(?,?);", new Object[]{person.getName(), person.getAge()});
            db.close();
        }
    }

    public void delete(int id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("delete from person where _id = ?;", new Integer[]{id});
            db.close();
        }
    }

    public void update(int id, String newName) {
        SQLiteDatabase db = helper.getWritableDatabase();
        if (db.isOpen()) {
            db.execSQL("update person set name = ? where _id= ?;", new Object[]{newName, id});
            db.close();
        }
    }

    public List<Person> queryAll() {
        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from person", null);
            if (cursor != null && cursor.getCount() > 0) {
                List<Person> persons = new ArrayList<Person>();
                int id;
                String name;
                int age;
                while (cursor.moveToNext()) {
                    id = cursor.getInt(0);
                    name = cursor.getString(1);
                    age = cursor.getInt(2);
                    persons.add(new Person(id, name, age));
                }
                db.close();
                return persons;
            }
            db.close();
        }
        return null;
    }

    public Person queryItem(int id) {
        SQLiteDatabase db = helper.getReadableDatabase();
        if (db.isOpen()) {
            Cursor cursor = db.rawQuery("select * from person where _id = ?;", new String[]{String.valueOf(id)});
            if (cursor != null && cursor.moveToFirst()) {
                int _id = cursor.getInt(0);
                String name = cursor.getString(1);
                int age = cursor.getInt(2);
                db.close();
                return new Person(_id, name, age);
            }
            db.close();
        }
        return null;
    }

}
