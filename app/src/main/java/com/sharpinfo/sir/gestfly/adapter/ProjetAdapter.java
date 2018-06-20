package com.sharpinfo.sir.gestfly.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.projet.ProjetContentActivity;
import com.sharpinfo.sir.gestfly.action.projet.ProjetListActivity;
import com.sharpinfo.sir.gestfly.action.rapport.RapportContentActivity;
import com.sharpinfo.sir.gestfly.bean.Projet;
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

public class ProjetAdapter extends RecyclerView.Adapter<ProjetAdapter.ViewHolder> {

    private List<Projet> mProjets;
    private AlertDialog alertDialog;

    public ProjetAdapter(List<Projet> projets) {
        mProjets = projets;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public ConstraintLayout projetitem;


        public ViewHolder(View itemView) {
            super(itemView);

            projetitem = itemView.findViewById(R.id.projet_item);
            nom = itemView.findViewById(R.id.projet_item_nom);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        final Integer flagProjet = (Integer) Session.getAttribut("flagProjet");
//        Log.d("tag", "Session/flagProjet ================= " + flagProjet);
        final Integer startWork = 0;
        final Integer finishWork = 1;
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        final View projetView = inflater.inflate(R.layout.item_projet_list, parent, false);

        final ProjetAdapter.ViewHolder viewHolder = new ProjetAdapter.ViewHolder(projetView);


        viewHolder.projetitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Session.setAttribute(mProjets.get(viewHolder.getAdapterPosition()), "clickedProjet");

                Dispacher.forward(context, ProjetContentActivity.class);
//                final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                View mView = inflater.inflate(R.layout.projet_info_popup, null);
//                ImageButton dismissButton = mView.findViewById(R.id.dismiss_info_projet);
//                TextView nomProjet = mView.findViewById(R.id.nom_projet_info_popup);
//                TextView dateCreation = mView.findViewById(R.id.dateCreation_projet_info_popup);
//                TextView dateRealisation = mView.findViewById(R.id.dateRealisation_projet_info_popup);
//                TextView etatProjet = mView.findViewById(R.id.etat_projet_info_popup);
//                final TextView chefProjet = mView.findViewById(R.id.chef_projet_info_popup);
//                final Button projetStart = mView.findViewById(R.id.btn_start_projet_info_popup);
//                final Button projetFinish = mView.findViewById(R.id.btn_finish_projet_info_popup);
//
//                projetStart.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        if (flagProjet == 0) {
//                            projetStart.setVisibility(View.GONE);
//                            projetFinish.setVisibility(View.VISIBLE);
//                            Session.updateAttribute(finishWork, "flagProjet");
//                            Log.d("tag", "Session ======= 1");
////                        } else {
////                            Toast.makeText(context, "Il y a déjà un projet en cours de travail !", Toast.LENGTH_LONG).show();
////                        }
//                    }
//                });
//
//                projetFinish.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        projetFinish.setVisibility(View.GONE);
//                        projetStart.setVisibility(View.VISIBLE);
//                        Session.updateAttribute(startWork, "flagProjet");
//                        Log.d("tag", "Session ======= 0");
//                    }
//                });
//
//                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//                String dateStringCreation = dateFormat.format(mProjets.get(viewHolder.getAdapterPosition()).getDateCreation());
//                String dateStringRealisaton = dateFormat.format(mProjets.get(viewHolder.getAdapterPosition()).getDateDebut());
//
//                dateCreation.setText(dateStringCreation);
//                dateRealisation.setText(dateStringRealisaton);
//
//                nomProjet.setText(mProjets.get(viewHolder.getAdapterPosition()).getNom());
//
//                Long etat = mProjets.get(viewHolder.getAdapterPosition()).getEtat_id();
//                Log.d("projetADapted", String.valueOf(etat));
//
//                if (etat == 1) {
//                    etatProjet.setText("Validé");
//                } else if (etat == 2) {
//                    etatProjet.setText("Non Validé");
//                } else if (etat == 3) {
//                    etatProjet.setText("Soumission");
//                } else if (etat == 4) {
//                    etatProjet.setText("Preparation de documents");
//                } else {
//                    etatProjet.setText("erreur du serveur");
//                }
//
//                Long userId = mProjets.get(viewHolder.getAdapterPosition()).getUser_id();
//
//                ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
//                Call<User> call = apiInterface.findUser(userId);
//                call.enqueue(new Callback<User>() {
//                    @Override
//                    public void onResponse(Call<User> call, Response<User> response) {
//                        User user = response.body();
//                        Log.d("projetADAPATER---------", user.toString());
//                        Log.d("projetADAPATER", response.body().toString());
//                        if (user == null) {
//                            chefProjet.setText("Erreur du serveur");
//                        } else {
//                            chefProjet.setText(String.format("%s %s", user.getFirstName(), user.getLastName()));
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<User> call, Throwable t) {
//
//                    }
//                });
//
//
//                builder.setView(mView);
//                alertDialog = builder.create();
//
//                dismissButton.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        alertDialog.dismiss();
//                    }
//                });
//
//                alertDialog.show();
            }


        });


        return viewHolder;
    }

    private List<User> findUser(Long userId) {
        final List<User> users = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<User> call = apiInterface.findUser(userId);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                users.add(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        return users;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Projet projet = mProjets.get(position);

        TextView nomTextView = viewHolder.nom;
        nomTextView.setText(projet.getNom());
    }

    public void setfilter(List<Projet> filteredprojets) {
        mProjets = new ArrayList<>();
        mProjets.addAll(filteredprojets);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mProjets.size();
    }
}
