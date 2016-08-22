package com.seventytimesseven.servicefusion.Models;

import com.orm.SugarRecord;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jimmy on 8/17/2016.
 */
public class Person extends SugarRecord{
    public String FirstName;
    public String LastName;
    public long DateOfBirth;
    public Integer ZipCode;

    public Person(){}

    public Person(String pFirstName, String pLastName, long pDateOfBirth, Integer pZipCode){
        FirstName = pFirstName;
        LastName = pLastName;
        DateOfBirth = pDateOfBirth;
        ZipCode = pZipCode;
    }

    @Override
    public String toString() {
        SimpleDateFormat fmtOut = new SimpleDateFormat("EEE, MMM d, yyyy");

        return FirstName + " " + LastName + "\n" + fmtOut.format(new Date(DateOfBirth)) + "\n" + ZipCode;
    }
}
