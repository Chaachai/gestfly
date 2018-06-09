package com.sharpinfo.sir.gestfly.action.salaire;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.sharpinfo.sir.gestfly.R;

import java.util.Calendar;

public class DemandeAvanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demande_avance);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }

        EditText year = findViewById(R.id.year);
        Log.d("tag", "year ========= "+year.getText());

        Calendar c = Calendar.getInstance();
        int thisYear = c.get(Calendar.YEAR) % 100;
        Log.d("tag", "this year ========= 20"+thisYear);
        year.setText("20"+thisYear);
    }
}
