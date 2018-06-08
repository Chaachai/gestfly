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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.adapter.TacheAdapter;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.helper.SimpleDividerItemDecoration;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class TacheListActivity extends AppCompatActivity {
    private static final String TAG = "TacheList";
    private Context mContext = this;
    RecyclerView tacheRecyclerView;
    SearchView searchView;
    TacheAdapter tacheAdapter;

    List<Tache> taches;

    private void injecterGUI() {
        tacheRecyclerView = findViewById(R.id.tacheRecyclerView);
    }

    private void initAdapter() {
        User user = (User) Session.getAttribut("connectedUser");
        taches = findTachesByUser(user.getId());

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

    private List<Tache> findTachesByUser(Long id) {
        final List<Tache> res = new ArrayList();
        Log.d(TAG, "executeApiCall");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Log.d(TAG, "ID ===== "+id);
        retrofit2.Call<List<Tache>> call = apiInterface.getTachesByUser(id);

        call.enqueue(new Callback<List<Tache>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Tache>> call, Response<List<Tache>> response) {
                Log.d(TAG, "onresponse");
                if (response.body() == null) {
                    Log.d(TAG, "RESPONSE NUUUULLLL !!!");
                } else if (response.body() != null) {
                    for (Tache tache : response.body()) {
                        res.add(tache);
                        Log.d(TAG, "*********>>>>> " + tache.getNom());
                    }

                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<Tache>> call, Throwable t) {
                Log.d(TAG, "failure");
                t.printStackTrace();
                Log.d(TAG, t.toString());
                Toast.makeText(TacheListActivity.this, "Failure !",
                        Toast.LENGTH_SHORT).show();
//                if (t instanceof IOException) {
//                    Toast.makeText(LoginActivity.this, "this is an actual network failure :( inform the user and possibly retry", Toast.LENGTH_SHORT).show();
//                    // logging probably not necessary
//                }
//                else {
//                    Toast.makeText(LoginActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
//                    // todo log to some central bug tracking service
//                }
            }
        });
        return res;

    }
}
