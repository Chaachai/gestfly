package com.sharpinfo.sir.gestfly.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.projet.ProjetListActivity;
import com.sharpinfo.sir.gestfly.bean.Projet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProjetAdapter extends RecyclerView.Adapter<ProjetAdapter.ViewHolder> {
    private List<Projet> mProjets;

    public ProjetAdapter(List<Projet> projets) {
        mProjets = projets;
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
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        final View projetView = inflater.inflate(R.layout.item_projet_list, parent, false);

        final ProjetAdapter.ViewHolder viewHolder = new ProjetAdapter.ViewHolder(projetView);

        return viewHolder;
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
