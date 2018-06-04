package com.sharpinfo.sir.gestfly.action.tache;

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
import com.sharpinfo.sir.gestfly.adapter.TacheAdapter;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class TacheListActivity extends AppCompatActivity {

    private Context mContext = this;
    RecyclerView tacheRecyclerView;
    SearchView searchView;
    TacheAdapter tacheAdapter;

    List<Tache> taches;

    private void injecterGUI() {
        tacheRecyclerView = findViewById(R.id.tacheRecyclerView);
    }

    private void initAdapter() {
        taches = new ArrayList<>();     // REPLACE THE ARRAY LIST WITH THE LIST FROM THE SERVER...

        // *************************************************************************************************

        Tache t1 = new Tache();
        Tache t2 = new Tache();
        Tache t3 = new Tache();
        Tache t4 = new Tache();

        t1.setNom("Nom de la tâch 1");
        t2.setNom("Nom de la tâch 2");
        t3.setNom("Nom de la tâch 3");
        t4.setNom("Nom de la tâch 4");

        taches.add(t1);
        taches.add(t2);
        taches.add(t3);
        taches.add(t4);

        // *************************************************************************************************

        tacheAdapter = new TacheAdapter(taches);

        tacheRecyclerView.setAdapter(tacheAdapter);
        tacheRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));

        tacheRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                getApplicationContext()
        ));

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache_list);
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
                final List<Tache> filteredTaches = filter(taches, newText);
                tacheAdapter.setfilter(filteredTaches);
                return true;
            }
        });
        return true;
    }

    private List<Tache> filter(List<Tache> tachesUnfiltered, String query) {
        query = query.toLowerCase();
        final List<Tache> filteredTaches = new ArrayList<>();
        for (Tache tache : tachesUnfiltered) {
            final String text = tache.getNom().toLowerCase();
            if (text.contains(query)) {
                filteredTaches.add(tache);
            }
        }
        return filteredTaches;
    }

}
