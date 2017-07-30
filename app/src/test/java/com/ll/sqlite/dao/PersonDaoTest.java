package com.ll.sqlite.dao;

import android.content.Context;
import android.test.mock.MockContext;

import com.ll.sqlite.db.PersonSQLiteOpenHelper;
import com.ll.sqlite.model.Person;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

/**
 * describe
 * Created by LiLei on 2017/7/18.Go.
 */
public class PersonDaoTest extends TestCase {
    private PersonSQLiteOpenHelper helper;
    private Context context;

    @Before
    public void setUp() throws Exception {
        context = new MockContext();
        helper = new PersonSQLiteOpenHelper(context);
        helper.getReadableDatabase();
    }

    @Test
    public void testInsert() throws Exception {
        PersonDao dao = new PersonDao(context);
        dao.insert(new Person(1, "友人A", 18));
    }
}