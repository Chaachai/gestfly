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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.adapter.RapportAdapter;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Rapport;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        User user = (User) Session.getAttribut("connectedUser");
        rapports = findRapportsByUser(user.getId());

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

    private List<Rapport> findRapportsByUser(Long userId) {
        final List<Rapport> res = new ArrayList<>();

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Rapport>> call = apiInterface.getRapportsByUser(userId);

        call.enqueue(new Callback<List<Rapport>>() {
            @Override
            public void onResponse(Call<List<Rapport>> call, Response<List<Rapport>> response) {
                List<Rapport> rapports = response.body();
                if (rapports == null) {
                    Toast.makeText(RapportListActivity.this, "vous n\'avez aucun rapport", Toast.LENGTH_SHORT).show();
                } else {
                    for (Rapport rapport : rapports) {
//                        Log.d("tag", rapport.toString());
                        Log.d("tag", "PROJET ========= " + rapport.getProjet());
                        Log.d("tag", "TACHE ========= " + rapport.getTache());
                        Log.d("tag", "IMAGE ========= " + rapport.getImage());
                        Log.d("tag", "USER ========= " + rapport.getUser());
                        res.add(rapport);
                    }
                    rapportAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Rapport>> call, Throwable t) {
                t.printStackTrace();
                Log.d("tag", t.toString());
                Toast.makeText(RapportListActivity.this, "Echec , verifiez votre connexion !", Toast.LENGTH_SHORT).show();
            }
        });
        return res;
    }

}
