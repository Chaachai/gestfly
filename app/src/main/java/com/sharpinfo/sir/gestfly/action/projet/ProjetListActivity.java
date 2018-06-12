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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.tache.TacheListActivity;
import com.sharpinfo.sir.gestfly.adapter.ProjetAdapter;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjetListActivity extends AppCompatActivity {

    public static final String TAG = "ProjetListActivity";

    private Context mContext = this;
    RecyclerView projetRecyclerView;
    SearchView searchView;
    ProjetAdapter projetAdapter;

    List<Projet> projets;

    private void injecterGUI() {
        projetRecyclerView = findViewById(R.id.projetRecyclerView);
    }


    private void initAdapter() {
        User user = (User) Session.getAttribut("connectedUser");
        Log.d(TAG, String.valueOf(user.getId()));
        projets = findProjetsByUser(user.getId());

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


    private List<Projet> findProjetsByUser(Long userId) {
        final List<Projet> res = new ArrayList<>();

        Log.d(TAG, "executeApiCall");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Projet>> call = apiInterface.getProjetsByUser(userId);

        call.enqueue(new Callback<List<Projet>>() {
            @Override
            public void onResponse(Call<List<Projet>> call, Response<List<Projet>> response) {
                Log.d(TAG, "Onresponse");
                List<Projet> prjets = response.body();
                if (prjets == null) {
                    Toast.makeText(ProjetListActivity.this, "Aucun projet ne vous est affecté", Toast.LENGTH_SHORT).show();
                } else {
                    for (Projet projet : prjets) {
                        Log.d(TAG, projet.toString());
                        Log.d(TAG, projet.getCreator().toString());
                        res.add(projet);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Projet>> call, Throwable t) {
                Log.d(TAG, "OnFailure");
                t.printStackTrace();
                Log.d(TAG, t.toString());
                Toast.makeText(ProjetListActivity.this, "Echec , verifiez votre connexion !", Toast.LENGTH_SHORT).show();
            }
        });
        return res;
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
