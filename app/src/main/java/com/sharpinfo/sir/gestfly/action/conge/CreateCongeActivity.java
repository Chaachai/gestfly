package com.sharpinfo.sir.gestfly.action.conge;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.menu.MenuTechnicienActivity;
import com.sharpinfo.sir.gestfly.bean.Conge;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateCongeActivity extends AppCompatActivity {

    private EditText editDateMin;
    private EditText editDateMax;
    private Calendar myCalendarMin = Calendar.getInstance();
    private Calendar myCalendarMax = Calendar.getInstance();
    private Button button;

    Context context = this;
//    String dateFormat = "dd-MM-yyyy";
    String dateFormat = "yyyy-MM-dd";
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

    private void executeApiCall(String dmin, String dmax) {
        Conge conge = new Conge();


        User user = (User) Session.getAttribut("connectedUser");
        Log.d("haaaaa user", user.toString());
        conge.setUser_id(user.getId());

        Log.d("createConge", conge.toString());
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

//        Call<Integer> call = apiInterface.createConge(conge);
        Call<Integer> call = apiInterface.createConge(dmin, dmax, user.getId());
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.body() == 1) {
                    Toast.makeText(CreateCongeActivity.this, "Conge cree avec succes", Toast.LENGTH_SHORT).show();
                    Dispacher.forward(CreateCongeActivity.this, CongeListActivity.class);
                    finish();
                } else {
                    Toast.makeText(CreateCongeActivity.this, "Une erreur est survenu lors de la creation", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Toast.makeText(CreateCongeActivity.this, "Verifiez votre connexion !",
                        Toast.LENGTH_SHORT).show();
            }
        });
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

        button = findViewById(R.id.valider_conge_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dMin = String.valueOf(editDateMin.getText());
                String dMax = String.valueOf(editDateMax.getText());
                executeApiCall(dMin, dMax);

            }
        });


    }
}
