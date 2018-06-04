package com.sharpinfo.sir.gestfly.action.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.action.conge.CongeListActivity;
import com.sharpinfo.sir.gestfly.action.projet.ProjetListActivity;
import com.sharpinfo.sir.gestfly.action.salaire.SalaireListActivity;
import com.sharpinfo.sir.gestfly.action.tache.TacheListActivity;
import com.sharpinfo.sir.gestfly.helper.Dispacher;

public class MenuTechnicienActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView projetCard;
    private CardView tacheCard;
    private CardView congeCard;
    private CardView salaireCard;
    private CardView rapportCard;
    private CardView parametreCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_technicien);

        projetCard = findViewById(R.id.projetCardView);
        tacheCard = findViewById(R.id.tacheCardView);
        congeCard = findViewById(R.id.congeCardView);
        salaireCard = findViewById(R.id.salaireCardView);

        projetCard.setOnClickListener(this);
        tacheCard.setOnClickListener(this);
        congeCard.setOnClickListener(this);
        salaireCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.projetCardView:
                Dispacher.forward(MenuTechnicienActivity.this, ProjetListActivity.class);
                break;
            case R.id.tacheCardView:
                Dispacher.forward(MenuTechnicienActivity.this, TacheListActivity.class);
                break;
            case R.id.congeCardView:
                Dispacher.forward(MenuTechnicienActivity.this, CongeListActivity.class);
                break;
            case R.id.salaireCardView:
                Dispacher.forward(MenuTechnicienActivity.this, SalaireListActivity.class);
                break;
            default:
                break;
        }
    }


}
