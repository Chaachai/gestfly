package com.sharpinfo.sir.gestfly.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.bean.Conge;
import com.sharpinfo.sir.gestfly.bean.DemandeSalaire;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SalaireAdapter extends RecyclerView.Adapter<SalaireAdapter.ViewHolder> {
    private List<DemandeSalaire> mSalaires;

    public SalaireAdapter(List<DemandeSalaire> salaires) {
        mSalaires = salaires;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView etat;
        public ImageView seeMore;
        public ConstraintLayout salaireItem;


        public ViewHolder(View itemView) {
            super(itemView);

            salaireItem = itemView.findViewById(R.id.salaire_item);
            seeMore = itemView.findViewById(R.id.see_more_salaire);
            textView1 = itemView.findViewById(R.id.salaire_textView1);
            textView2 = itemView.findViewById(R.id.salaire_textView2);
            textView3 = itemView.findViewById(R.id.salaire_textView3);
            etat = itemView.findViewById(R.id.salaire_item_etat);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        final View salaireView = inflater.inflate(R.layout.item_salaire_list, parent, false);

        final SalaireAdapter.ViewHolder viewHolder = new SalaireAdapter.ViewHolder(salaireView);

        viewHolder.seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, viewHolder.seeMore);
                if (!mSalaires.get(viewHolder.getAdapterPosition()).getEtat().getLibelle().equalsIgnoreCase("En cours")) {

                    popupMenu.inflate(R.menu.delete_option);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.delete_item:

                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                    builder1.setTitle("Confirmer ?");
                                    builder1.setMessage("Voulez-vous vraiment supprimer cette demande ?");
                                    builder1.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {

                                            // CALL THE METHOD THAT DELETES THE SALAIRE ITEM .......
                                            removeFromList(viewHolder.getAdapterPosition(), viewHolder, context);

                                            dialog.dismiss();
                                            Snackbar.make(salaireView, "La demande a été supprimée avec succès", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        }
                                    });

                                    builder1.setNegativeButton("Non", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            dialog.dismiss();
                                        }
                                    });

                                    AlertDialog alert = builder1.create();
                                    alert.show();

                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                } else {
                    popupMenu.inflate(R.menu.options_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()) {
                                case R.id.delete_item_options_menu:

                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                                    builder1.setTitle("Confirmer ?");
                                    builder1.setMessage("Voulez-vous vraiment supprimer cette demande ?");
                                    builder1.setPositiveButton("Oui", new DialogInterface.OnClickListener() {

                                        public void onClick(DialogInterface dialog, int which) {

                                            // CALL THE METHOD THAT DELETES THE SALAIRE ITEM .......
                                            removeFromList(viewHolder.getAdapterPosition(), viewHolder, context);


                                            dialog.dismiss();
                                            Snackbar.make(salaireView, "La demande a été supprimée avec succès", Snackbar.LENGTH_LONG)
                                                    .setAction("Action", null).show();
                                        }
                                    });

                                    builder1.setNegativeButton("Non", new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                            // Do nothing
                                            dialog.dismiss();
                                        }
                                    });

                                    AlertDialog alert = builder1.create();
                                    alert.show();

                                    break;
                                case R.id.edit_item_options_menu:

//                                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                                    final View mView = inflater.inflate(R.layout.edit_conge_popup, null);
//
//                                    ImageButton dismissButton = mView.findViewById(R.id.dismiss_edit_conge);
//
//                                    final EditText editDateMin = mView.findViewById(R.id.editDateMinConge);
//                                    final EditText editDateMax = mView.findViewById(R.id.editDateMaxConge);
//                                    Button button = mView.findViewById(R.id.modifier_conge_btn);
//
//                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
//                                    String dateMinString = dateFormat.format(mSalaires.get(viewHolder.getAdapterPosition()).getDateDebut());
//                                    String dateMaxString = dateFormat.format(mSalaires.get(viewHolder.getAdapterPosition()).getDateReprise());
//
//                                    editDateMin.setText(dateMinString);
//                                    editDateMax.setText(dateMaxString);
//
//                                    builder.setView(mView);
//                                    final AlertDialog alertDialog = builder.create();
//
//                                    button.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            Conge conge = mSalaires.get(viewHolder.getAdapterPosition());
//
//                                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
//                                            try {
//                                                Date dateMinEdited = format.parse(editDateMin.getText() + "");
//                                                Date dateMaxEdited = format.parse(editDateMax.getText() + "");
//                                                conge.setDateDebut(dateMinEdited);
//                                                conge.setDateReprise(dateMaxEdited);
//                                            } catch (ParseException e) {
//                                                e.printStackTrace();
//                                            }
//
//                                            // CALL THE METHODE THAT MAKES THE UPDATE HERE ...............
//
//                                            notifyDataSetChanged();
//                                            alertDialog.dismiss();
//
//                                            Snackbar snackbar = Snackbar.make(salaireView, "La demande a été modifiée avec succès", Snackbar.LENGTH_LONG);
//                                            snackbar.show();
//                                        }
//                                    });
//
//                                    dismissButton.setOnClickListener(new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View view) {
//                                            alertDialog.dismiss();
//                                        }
//                                    });
//
//                                    alertDialog.show();

                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        DemandeSalaire salaire = mSalaires.get(position);

        TextView etatTextView = viewHolder.etat;
        TextView firstTextView = viewHolder.textView1;
        TextView secondTextView = viewHolder.textView2;
        TextView thirdTextView = viewHolder.textView3;

        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/yyyy", Locale.getDefault());
        String dateString = "";
        if(salaire.getMoisAvancer() != null){
            dateString = dateFormat.format(salaire.getMoisAvancer());
        }


        if (salaire.getEtat().getLibelle().equalsIgnoreCase("Accepté")) {
            etatTextView.setTextColor(Color.parseColor("#01a844"));
        } else if (salaire.getEtat().getLibelle().equalsIgnoreCase("Refusé")) {
            etatTextView.setTextColor(Color.parseColor("#d60c0c"));
        } else if (salaire.getEtat().getLibelle().equalsIgnoreCase("En cours")) {
            etatTextView.setTextColor(Color.parseColor("#000000"));
        } else {
            etatTextView.setTextColor(Color.parseColor("#000000"));
        }
        etatTextView.setText(salaire.getEtat().getLibelle());

        if(salaire.getType().getType().equalsIgnoreCase("augmentation")){
            firstTextView.setText(R.string.augmentation_de);
            secondTextView.setText(salaire.getMontantAjouter()+"");
            thirdTextView.setText(R.string.dhs);
        }else if(salaire.getType().getType().equalsIgnoreCase("avance")){
            firstTextView.setText(R.string.avance_du_mois);
            secondTextView.setText(dateString);
            thirdTextView.setText("");
        }else{
            firstTextView.setText("");
            secondTextView.setText("");
            thirdTextView.setText("");
        }

    }

    public void removeFromList(int position, ViewHolder viewHolder, Context context) {
        DemandeSalaire salaire = mSalaires.get(viewHolder.getAdapterPosition());

        mSalaires.remove(position);
        notifyItemRemoved(viewHolder.getAdapterPosition());
        notifyItemRangeChanged(viewHolder.getAdapterPosition(), mSalaires.size());

    }

    public void setfilter(List<DemandeSalaire> filteredSalaire) {
        mSalaires = new ArrayList<>();
        mSalaires.addAll(filteredSalaire);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mSalaires.size();
    }
}
