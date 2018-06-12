package com.sharpinfo.sir.gestfly.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.sharpinfo.sir.gestfly.action.conge.CongeListActivity;
import com.sharpinfo.sir.gestfly.bean.Conge;
import com.sharpinfo.sir.gestfly.bean.Tache;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CongeAdapter extends RecyclerView.Adapter<CongeAdapter.ViewHolder> {
    private List<Conge> mConges;

    public CongeAdapter(List<Conge> conges) {
        mConges = conges;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dateMin;
        public TextView dateMax;
        public TextView etat;
        public ImageView seeMore;
        public ConstraintLayout congeItem;


        public ViewHolder(View itemView) {
            super(itemView);

            congeItem = itemView.findViewById(R.id.conge_item);
            seeMore = itemView.findViewById(R.id.see_more_conge);
            dateMin = itemView.findViewById(R.id.conge_date_min);
            dateMax = itemView.findViewById(R.id.conge_date_max);
            etat = itemView.findViewById(R.id.conge_item_etat);

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final Context context = parent.getContext();
        final LayoutInflater inflater = LayoutInflater.from(context);

        final View congeView = inflater.inflate(R.layout.item_conge_list, parent, false);

        final CongeAdapter.ViewHolder viewHolder = new CongeAdapter.ViewHolder(congeView);

        viewHolder.seeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, viewHolder.seeMore);
                if (!mConges.get(viewHolder.getAdapterPosition()).getEtat().getLibelle().equalsIgnoreCase("En cours")) {

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

                                            // CALL THE METHOD THAT DELETES THE CONGE .......
                                            removeFromList(viewHolder.getAdapterPosition(), viewHolder, context);

                                            dialog.dismiss();
                                            Snackbar.make(congeView, "La demande a été supprimée avec succès", Snackbar.LENGTH_LONG)
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

                                            // CALL THE METHOD THAT DELETES THE CONGE .......
                                            removeFromList(viewHolder.getAdapterPosition(), viewHolder, context);


                                            dialog.dismiss();
                                            Snackbar.make(congeView, "La demande a été supprimée avec succès", Snackbar.LENGTH_LONG)
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

                                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                    final View mView = inflater.inflate(R.layout.edit_conge_popup, null);

                                    ImageButton dismissButton = mView.findViewById(R.id.dismiss_edit_conge);

                                    final EditText editDateMin = mView.findViewById(R.id.editDateMinConge);
                                    final EditText editDateMax = mView.findViewById(R.id.editDateMaxConge);
                                    Button button = mView.findViewById(R.id.modifier_conge_btn);

                                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                                    String dateMinString = dateFormat.format(mConges.get(viewHolder.getAdapterPosition()).getDateDebut());
                                    String dateMaxString = dateFormat.format(mConges.get(viewHolder.getAdapterPosition()).getDateReprise());

                                    editDateMin.setText(dateMinString);
                                    editDateMax.setText(dateMaxString);

                                    builder.setView(mView);
                                    final AlertDialog alertDialog = builder.create();

                                    button.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Conge conge = mConges.get(viewHolder.getAdapterPosition());

                                            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                                            try {
                                                Date dateMinEdited = format.parse(editDateMin.getText() + "");
                                                Date dateMaxEdited = format.parse(editDateMax.getText() + "");
                                                conge.setDateDebut(dateMinEdited);
                                                conge.setDateReprise(dateMaxEdited);
                                            } catch (ParseException e) {
                                                e.printStackTrace();
                                            }

                                            // CALL THE METHODE THAT MAKES THE UPDATE HERE ...............

                                            notifyDataSetChanged();
                                            alertDialog.dismiss();

                                            Snackbar snackbar = Snackbar.make(congeView, "La demande a été modifiée avec succès", Snackbar.LENGTH_LONG);
                                            snackbar.show();
                                        }
                                    });

                                    dismissButton.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            alertDialog.dismiss();
                                        }
                                    });

                                    alertDialog.show();

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
        Conge conge = mConges.get(position);

        TextView nomTextView = viewHolder.etat;
        TextView dateMinTextView = viewHolder.dateMin;
        TextView dateMaxTextView = viewHolder.dateMax;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String dateMinString = dateFormat.format(conge.getDateDebut());
        String dateMaxString = dateFormat.format(conge.getDateReprise());

        if (conge.getEtat().getLibelle().equalsIgnoreCase("Acceptée")) {
            nomTextView.setTextColor(Color.parseColor("#01a844"));
        } else if (conge.getEtat().getLibelle().equalsIgnoreCase("Refusée")) {
            nomTextView.setTextColor(Color.parseColor("#d60c0c"));
        } else if (conge.getEtat().getLibelle().equalsIgnoreCase("En cours")) {
            nomTextView.setTextColor(Color.parseColor("#000000"));
        } else {
            nomTextView.setTextColor(Color.parseColor("#000000"));
        }
        nomTextView.setText(conge.getEtat().getLibelle());
        dateMinTextView.setText(dateMinString);
        dateMaxTextView.setText(dateMaxString);

    }

    public void removeFromList(int position, ViewHolder viewHolder, Context context) {
        Conge conge = mConges.get(viewHolder.getAdapterPosition());

        mConges.remove(position);
        notifyItemRemoved(viewHolder.getAdapterPosition());
        notifyItemRangeChanged(viewHolder.getAdapterPosition(), mConges.size());

    }

    public void setfilter(List<Conge> filteredConges) {
        mConges = new ArrayList<>();
        mConges.addAll(filteredConges);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mConges.size();
    }
}
