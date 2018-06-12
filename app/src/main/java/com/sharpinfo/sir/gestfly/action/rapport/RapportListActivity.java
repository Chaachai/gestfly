package com.sharpinfo.sir.gestfly.action.rapport;

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
import android.widget.EditText;
import android.widget.ImageButton;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.adapter.RapportAdapter;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Rapport;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class RapportListActivity extends AppCompatActivity {

    private Context mContext = this;
    RecyclerView rapportRecyclerView;
    SearchView searchView;
    RapportAdapter rapportAdapter;
    AlertDialog alertDialog;
    Integer rapportFlag = null;
    List<Rapport> rapports;

    private void injecterGUI() {
        rapportRecyclerView = findViewById(R.id.rapportRecyclerView);
    }

    private void initAdapter() {
        rapports = new ArrayList<>();     // REPLACE THE ARRAY LIST WITH THE LIST FROM THE SERVER...

        // *************************************************************************************************

        Rapport r1 = new Rapport();
        Rapport r2 = new Rapport();
        Rapport r3 = new Rapport();

        User user = (User) Session.getAttribut("connectedUser");

        Projet p1 = new Projet();
        Projet p2 = new Projet();
        p1.setNom("Nom du projet 1");
        p2.setNom("Nom du projet 2");

        Tache t = new Tache();
        t.setNom("Nom de la tache ");

        r1.setProjet(p1);
        r2.setTache(t);
        r3.setProjet(p2);

        r1.setTitre("Titre du rapport 1");
        r2.setTitre("Titre du rapport 2");
        r3.setTitre("Titre du rapport 3");

        r1.setUser(user);
        r2.setUser(user);
        r3.setUser(user);

        r1.setText("Solomon Grundy," +
                "Born on a Monday," +
                "Christened on Tuesday," +
                "Married on Wednesday," +
                "Took ill on Thursday," +
                "Grew worse on Friday," +
                "Died on Saturday," +
                "Buried on Sunday," +
                "That was the end," +
                "Of Solomon Grundy.");

        r2.setText("Solomon Grundy," +
                "Born on a Monday," +
                "Christened on Tuesday," +
                "Married on Wednesday," +
                "Took ill on Thursday," +
                "Grew worse on Friday," +
                "Died on Saturday," +
                "Buried on Sunday," +
                "That was the end," +
                "Of Solomon Grundy.");

        r3.setText("Solomon Grundy," +
                "Born on a Monday," +
                "Christened on Tuesday," +
                "Married on Wednesday," +
                "Took ill on Thursday," +
                "Grew worse on Friday," +
                "Died on Saturday," +
                "Buried on Sunday," +
                "That was the end," +
                "Of Solomon Grundy.");

        rapports.add(r1);
        rapports.add(r2);
        rapports.add(r3);


        // *************************************************************************************************


        rapportAdapter = new RapportAdapter(rapports);

        rapportRecyclerView.setAdapter(rapportAdapter);
        rapportRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        rapportRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapport_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        injecterGUI();
        initAdapter();
        rapportFlag = 0;
        Session.setAttribute(rapportFlag, "rapportFlag");

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                View mView = getLayoutInflater().inflate(R.layout.creer_rapport_popup, null);

                ImageButton dismissButton = mView.findViewById(R.id.dismiss_create_rapport);


                builder.setView(mView);
                alertDialog = builder.create();

                dismissButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.searchfile, menu);
        final MenuItem myActionMenuItem = menu.findItem(R.id.search_id);
        searchView = (SearchView) myActionMenuItem.getActionView();
        ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text)).setHintTextColor(getResources().getColor(R.color.white));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }
                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                final List<Rapport> filteredRapports = filter(rapports, newText);
                rapportAdapter.setfilter(filteredRapports);
                return true;
            }
        });
        return true;
    }

    private List<Rapport> filter(List<Rapport> rapportsUnfiltered, String query) {
        query = query.toLowerCase();
        final List<Rapport> filteredRapports = new ArrayList<>();
        for (Rapport rapport : rapportsUnfiltered) {
            final String text = rapport.getTitre().toLowerCase();
            if (text.contains(query)) {
                filteredRapports.add(rapport);
            }
        }
        return filteredRapports;
    }

    public void goToRapportProjet(View view) {
        rapportFlag = 1;
        Session.updateAttribute(rapportFlag, "rapportFlag");
        Dispacher.forward(RapportListActivity.this, CreerRapportActivity.class);
        alertDialog.dismiss();
    }


    public void goToRapportTache(View view) {
        rapportFlag = 2;
        Session.updateAttribute(rapportFlag, "rapportFlag");
        Dispacher.forward(RapportListActivity.this, CreerRapportActivity.class);
        alertDialog.dismiss();
    }

}
