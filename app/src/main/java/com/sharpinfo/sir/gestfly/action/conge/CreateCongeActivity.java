package com.sharpinfo.sir.gestfly.action.conge;

import android.app.DatePickerDialog;
import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.sharpinfo.sir.gestfly.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CreateCongeActivity extends AppCompatActivity {

    private EditText editDateMin;
    private EditText editDateMax;
    private Calendar myCalendarMin = Calendar.getInstance();
    private Calendar myCalendarMax = Calendar.getInstance();

    Context context = this;
    String dateFormat = "dd-MM-yyyy";
    DatePickerDialog.OnDateSetListener dateMin;
    DatePickerDialog.OnDateSetListener dateMax;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat, Locale.getDefault());


    // ************************************************ DATE MIN **********************************************************

    private void updateDateMin() {
        editDateMin.setText(simpleDateFormat.format(myCalendarMin.getTime()));
    }

    private void initPopupDateMin() {
        editDateMin = findViewById(R.id.createDateMinConge);
        dateMin = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarMin.set(Calendar.YEAR, year);
                myCalendarMin.set(Calendar.MONTH, monthOfYear);
                myCalendarMin.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateMin();
            }

        };

        editDateMin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, dateMin, myCalendarMin
                        .get(Calendar.YEAR), myCalendarMin.get(Calendar.MONTH),
                        myCalendarMin.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void initDateMin() {
        long currentdate = System.currentTimeMillis();
        String dateString = simpleDateFormat.format(currentdate);
        editDateMin = findViewById(R.id.createDateMinConge);
        initPopupDateMin();
    }

    // ************************************************ DATE MAX **********************************************************

    private void updateDateMax() {
        editDateMax.setText(simpleDateFormat.format(myCalendarMax.getTime()));
    }

    private void initPopupDateMax() {
        editDateMax = findViewById(R.id.createDateMaxConge);
        dateMax = new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendarMax.set(Calendar.YEAR, year);
                myCalendarMax.set(Calendar.MONTH, monthOfYear);
                myCalendarMax.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateDateMax();
            }

        };

        editDateMax.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(context, dateMax, myCalendarMax
                        .get(Calendar.YEAR), myCalendarMax.get(Calendar.MONTH),
                        myCalendarMax.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void initDateMax() {
        long currentdate = System.currentTimeMillis();
        String dateString = simpleDateFormat.format(currentdate);
        editDateMax = findViewById(R.id.createDateMaxConge);
        initPopupDateMin();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_conge);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        editDateMin = findViewById(R.id.createDateMinConge);
        initPopupDateMin();
        initDateMin();

        editDateMax = findViewById(R.id.createDateMaxConge);
        initPopupDateMax();
        initDateMax();


    }
}
