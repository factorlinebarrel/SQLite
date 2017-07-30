package com.ll.sqlite.dao;

import android.test.LoaderTestCase;
import android.util.Log;

import com.ll.sqlite.db.PersonSQLiteOpenHelper;
import com.ll.sqlite.model.Person;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * describe
 * Created by LiLei on 2017/7/18.Go.
 */
public class PersonDaoTest extends LoaderTestCase {
    private PersonSQLiteOpenHelper helper;

    @Before
    public void setUp() throws Exception {
        helper = new PersonSQLiteOpenHelper(getContext());
        helper.getReadableDatabase();
    }

    @Test
    public void testInsert() throws Exception {
        PersonDao dao = new PersonDao(getContext());
        dao.insert(new Person(3, "友人A", 18));
    }

    @Test
    public void testDelete() throws Exception {
        PersonDao dao = new PersonDao(getContext());
        dao.delete(1);
    }

    @Test
    public void testUpdate() throws Exception {
        PersonDao dao = new PersonDao(getContext());
        dao.update(1, "友人B");
    }

    @Test
    public void testQueryAll() throws Exception {
        PersonDao dao = new PersonDao(getContext());
        List<Person> persons = dao.queryAll();
        Log.i("PersonDaoTest", persons.toString());
    }

    @Test
    public void testQueryItem() throws Exception {
        PersonDao dao = new PersonDao(getContext());
        Person person = dao.queryItem(1);
        Log.i("PersonDaoTest", person.toString());
    }
}