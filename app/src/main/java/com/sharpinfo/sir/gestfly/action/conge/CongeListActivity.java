package com.sharpinfo.sir.gestfly.action.conge;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.adapter.CongeAdapter;
import com.sharpinfo.sir.gestfly.bean.Conge;
import com.sharpinfo.sir.gestfly.bean.TypeEtat;
import com.sharpinfo.sir.gestfly.bean.TypeEtatConge;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CongeListActivity extends AppCompatActivity {

    private Context mContext = this;
    RecyclerView congeRecyclerView;
    CongeAdapter congeAdapter;

    List<Conge> conges;

    private void injecterGUI() {
        congeRecyclerView = findViewById(R.id.congeRecyclerView);
    }

    private void initAdapter() {
        conges = new ArrayList<>();     // REPLACE THE ARRAY LIST WITH THE LIST FROM THE SERVER...

        // *************************************************************************************************

        TypeEtatConge t1 = new TypeEtatConge();
        TypeEtatConge t2 = new TypeEtatConge();
        TypeEtatConge t3 = new TypeEtatConge();

        t1.setLibelle("Accepté");
        t2.setLibelle("Refusé");
        t3.setLibelle("En cours");

        Conge c1 = new Conge();
        Conge c2 = new Conge();
        Conge c3 = new Conge();
        Conge c4 = new Conge();

        c1.setEtat(t1);
        c2.setEtat(t2);
        c3.setEtat(t3);
        c4.setEtat(t3);

        c1.setDateDebut(new Date());
        c2.setDateDebut(new Date());
        c3.setDateDebut(new Date());
        c4.setDateDebut(new Date());

        c1.setDateReprise(new Date());
        c2.setDateReprise(new Date());
        c3.setDateReprise(new Date());
        c4.setDateReprise(new Date());

        conges.add(c1);
        conges.add(c2);
        conges.add(c3);
        conges.add(c4);

        // *************************************************************************************************

        congeAdapter = new CongeAdapter(conges);

        congeRecyclerView.setAdapter(congeAdapter);
        congeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        congeRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conge_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        injecterGUI();
        initAdapter();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dispacher.forward(CongeListActivity.this, CreateCongeActivity.class);
            }
        });
    }

}
