package com.sharpinfo.sir.gestfly.action.projet;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.adapter.ProjetAdapter;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class ProjetListActivity extends AppCompatActivity {

    private Context mContext = this;
    RecyclerView projetRecyclerView;
    SearchView searchView;
    ProjetAdapter projetAdapter;

    List<Projet> projets;

    private void injecterGUI() {
        projetRecyclerView = findViewById(R.id.projetRecyclerView);
    }

    private void initAdapter() {
        projets = new ArrayList<>();     // REPLACE THE ARRAY LIST WITH THE LIST FROM THE SERVER...

        // *************************************************************************************************

        Projet p1 = new Projet();
        Projet p2 = new Projet();
        Projet p3 = new Projet();
        Projet p4 = new Projet();

        p1.setNom("Nom du Projet1");
        p2.setNom("Nom du Projet2");
        p3.setNom("Nom du Projet3");
        p4.setNom("Nom du Projet4");

        projets.add(p1);
        projets.add(p2);
        projets.add(p3);
        projets.add(p4);

        // *************************************************************************************************


        projetAdapter = new ProjetAdapter(projets);

        projetRecyclerView.setAdapter(projetAdapter);
        projetRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        projetRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        injecterGUI();
        initAdapter();

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
                final List<Projet> filteredProjets = filter(projets, newText);
                projetAdapter.setfilter(filteredProjets);
                return true;
            }
        });
        return true;
    }

    private List<Projet> filter(List<Projet> projetsUnfiltered, String query) {
        query = query.toLowerCase();
        final List<Projet> filteredProjets = new ArrayList<>();
        for (Projet projet : projetsUnfiltered) {
            final String text = projet.getNom().toLowerCase();
            if (text.contains(query)) {
                filteredProjets.add(projet);
            }
        }
        return filteredProjets;
    }

}