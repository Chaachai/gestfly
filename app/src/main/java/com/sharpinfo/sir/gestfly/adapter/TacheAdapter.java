package com.sharpinfo.sir.gestfly.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.tache.TacheListActivity;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Tache;

import java.util.ArrayList;
import java.util.List;

public class TacheAdapter extends RecyclerView.Adapter<TacheAdapter.ViewHolder> {
    private List<Tache> mTaches;

    public TacheAdapter(List<Tache> taches) {
        mTaches = taches;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView nom;
        public ConstraintLayout tacheItem;


        public ViewHolder(View itemView) {
            super(itemView);

            tacheItem = itemView.findViewById(R.id.tache_item);
            nom = itemView.findViewById(R.id.tache_item_nom);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        final View tacheView = inflater.inflate(R.layout.item_tache_list, parent, false);

        final TacheAdapter.ViewHolder viewHolder = new TacheAdapter.ViewHolder(tacheView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Tache tache = mTaches.get(position);

        TextView nomTextView = viewHolder.nom;
        nomTextView.setText(tache.getNom());
    }

    public void setfilter(List<Tache> filteredTaches) {
        mTaches = new ArrayList<>();
        mTaches.addAll(filteredTaches);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTaches.size();
    }
}
