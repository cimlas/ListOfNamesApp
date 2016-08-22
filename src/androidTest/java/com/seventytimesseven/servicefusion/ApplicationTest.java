package com.seventytimesseven.servicefusion;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.seventytimesseven.servicefusion.Models.Person;

import junit.framework.Assert;

import java.util.Date;
import java.util.List;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    private String mFirstName = "John";
    private String mLastName = "Doe";
    private Date mDateOfBirth = new Date(1999, 12, 31);
    private Integer mZipCode = 11226;

    public ApplicationTest() {
        super(Application.class);
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    public void test_addPerson() throws Exception {
        Person p = new Person(mFirstName, mLastName, mDateOfBirth.getTime(), mZipCode);
        assertNotNull(p);
        p.save();

        Person savedPerson = Person.findById(Person.class, p.getId());
        assertNotNull(savedPerson);
        assertEquals(p.toString(), savedPerson.toString());

        // clean up
        Person.delete(p);
        Person deletedPerson = Person.findById(Person.class, p.getId());
        assertNull(deletedPerson);
    }

    public void test_deletePerson() throws Exception{
        Person p = new Person(mFirstName, mLastName, mDateOfBirth.getTime(), mZipCode);
        assertNotNull(p);
        p.save();
        Person savedPerson = Person.findById(Person.class, p.getId());
        assertNotNull(savedPerson);

        Person.delete(p);
        Person deletedPerson = Person.findById(Person.class, p.getId());
        assertNull(deletedPerson);
    }

    public void test_findPerson() throws Exception{
        Person p = new Person(mFirstName, mLastName, mDateOfBirth.getTime(), mZipCode);
        assertNotNull(p);
        p.save();
        Person savedPerson = Person.findById(Person.class, p.getId());
        assertNotNull(savedPerson);

        Person foundPerson = Person.findById(Person.class, p.getId());
        assertNotNull(foundPerson);
    }

//    public void test_deleteAllPerson() throws Exception{
//
//        Person.deleteAll(Person.class);
//
//        Assert.assertEquals(0, Person.count(Person.class));
//    }
}