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
import com.sharpinfo.sir.gestfly.bean.Rapport;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

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
        Rapport rapport = mRapports.get(position);

        TextView titreTextView = viewHolder.titre;
        TextView firstTextView = viewHolder.textView1;
        TextView dateTextView = viewHolder.dateRapport;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateString = dateFormat.format(rapport.getDate());

        titreTextView.setText(rapport.getTitre());
        dateTextView.setText(dateString);

        Log.d("tag", "Projet ====== " + rapport.getProjet());
        Log.d("tag", "Tache ====== " + rapport.getTache());


        if (rapport.getProjet().getNom() != null && rapport.getTache().getNom() == null) {
            firstTextView.setText(rapport.getProjet().getNom());
        } else if (rapport.getTache().getNom() != null && rapport.getProjet().getNom() == null) {
            firstTextView.setText(rapport.getTache().getNom());
        } else {
            firstTextView.setText("");
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
