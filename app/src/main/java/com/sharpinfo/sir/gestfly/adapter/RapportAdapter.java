package com.sharpinfo.sir.gestfly.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.rapport.RapportContentActivity;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Rapport;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RapportAdapter extends RecyclerView.Adapter<RapportAdapter.ViewHolder> {
    private List<Rapport> mRapports;

    public RapportAdapter(List<Rapport> rapports) {
        mRapports = rapports;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        TextView dateRapport;
        public TextView titre;
        ConstraintLayout rapportItem;


        ViewHolder(View itemView) {
            super(itemView);

            rapportItem = itemView.findViewById(R.id.rapport_item);
            textView1 = itemView.findViewById(R.id.tache_ou_projet);
            titre = itemView.findViewById(R.id.rapport_item_titre);
            dateRapport = itemView.findViewById(R.id.date_rapport_item);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        final View rapportView = inflater.inflate(R.layout.item_rapport_list, parent, false);

        final RapportAdapter.ViewHolder viewHolder = new RapportAdapter.ViewHolder(rapportView);

        viewHolder.rapportItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Session.setAttribute(mRapports.get(viewHolder.getAdapterPosition()), "clickedRapport");

                Dispacher.forward(context, RapportContentActivity.class);
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        final Rapport rapport = mRapports.get(position);

        final TextView titreTextView = viewHolder.titre;
        final TextView firstTextView = viewHolder.textView1;
        final TextView dateTextView = viewHolder.dateRapport;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        final String dateString = dateFormat.format(rapport.getDate());

        if (rapport.getUser_id() != null) {
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<User> call = apiInterface.findUser(rapport.getUser_id());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    if (response.body() != null) {
                        User user = response.body();
                        rapport.setUser(user);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }


//        Log.d("tag", "Projet ====== " + rapport.getProjet());
//        Log.d("tag", "Tache ====== " + rapport.getTache());

        if (rapport.getProjet_id() == null && rapport.getTache_id() != null) {
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<Tache> call = apiInterface.findTache(rapport.getTache_id());
            call.enqueue(new Callback<Tache>() {
                @Override
                public void onResponse(Call<Tache> call, Response<Tache> response) {
                    Log.d("rapportAdapter", "knqlbo ela tache");
                    if (response.body() != null) {
                        titreTextView.setText(rapport.getTitre());
                        dateTextView.setText(dateString);
                        Tache tache = response.body();
                        rapport.setTache(tache);
                        firstTextView.setText(rapport.getTache().getNom());

                    }
                }

                @Override
                public void onFailure(Call<Tache> call, Throwable t) {

                }
            });
        } else {
            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<Projet> call = apiInterface.findProjet(rapport.getProjet_id());
            call.enqueue(new Callback<Projet>() {
                @Override
                public void onResponse(Call<Projet> call, Response<Projet> response) {
                    Log.d("rapportAdapter", "knqlbo ela projet");
                    if (response.body() != null) {
                        titreTextView.setText(rapport.getTitre());
                        dateTextView.setText(dateString);
                        Projet projet = response.body();
                        rapport.setProjet(projet);
                        firstTextView.setText(rapport.getProjet().getNom());
                    }
                }

                @Override
                public void onFailure(Call<Projet> call, Throwable t) {

                }
            });
        }


    }


    public void setfilter(List<Rapport> filteredRapport) {
        mRapports = new ArrayList<>();
        mRapports.addAll(filteredRapport);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mRapports.size();
    }
}
