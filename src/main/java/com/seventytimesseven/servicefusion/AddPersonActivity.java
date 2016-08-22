package com.seventytimesseven.servicefusion;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.seventytimesseven.servicefusion.Models.Person;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddPersonActivity extends AppCompatActivity {

    EditText mFirstNameEditText;
    EditText mLastNameEditText;
    EditText mZipEditText;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_person);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFirstNameEditText = (EditText)findViewById(R.id.edit_text_first_name);
        mLastNameEditText = (EditText)findViewById(R.id.edit_text_last_name);
        mZipEditText = (EditText)findViewById(R.id.edit_text_zip_code);

//        mFirstNameEditText.addTextChangedListener(new TextValidator(mFirstNameEditText) {
//            @Override public void validate(TextView textView, String text) {
//                mFab.setEnabled(validateText());
//            }
//        });
//
//        mLastNameEditText.addTextChangedListener(new TextValidator(mLastNameEditText) {
//            @Override public void validate(TextView textView, String text) {
//                mFab.setEnabled(validateText());
//            }
//        });
//
//        mZipEditText.addTextChangedListener(new TextValidator(mZipEditText) {
//            @Override public void validate(TextView textView, String text) {
//                mFab.setEnabled(validateText());
//            }
//        });

        mFab = (FloatingActionButton) findViewById(R.id.fab_add_person);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateText() == false){
                    Toast.makeText(view.getContext(), "Please fill text.", Toast.LENGTH_SHORT).show();
                    return;
                }

                DatePicker datePicker = ((DatePicker) findViewById(R.id.date_picker_date_of_birth));

                String firstName = ((EditText) findViewById(R.id.edit_text_first_name)).getText().toString();
                String lastName = ((EditText) findViewById(R.id.edit_text_last_name)).getText().toString();
                Calendar calendar = new GregorianCalendar(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
                Integer zipCode = Integer.parseInt((((EditText) findViewById(R.id.edit_text_zip_code)).getText().toString()));

                Person p = new Person(firstName, lastName, calendar.getTimeInMillis(), zipCode);
                p.save();

                Toast.makeText(view.getContext(), "Save successful!!!", Toast.LENGTH_SHORT).show();

                Context context = view.getContext();
                Intent intent = new Intent(context, PersonListActivity.class);
                context.startActivity(intent);

                finish();
            }
        });
    }

    private Boolean validateText() {

        if(mFirstNameEditText.getText().length() > 0
                && mLastNameEditText.getText().length() > 0
                && mZipEditText.getText().length() > 0){

            return true;
        }

        return false;
    }
}
