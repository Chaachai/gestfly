package com.sharpinfo.sir.gestfly.action.menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.TestUploadFilesActivity;
import com.sharpinfo.sir.gestfly.action.conge.CongeListActivity;
import com.sharpinfo.sir.gestfly.action.projet.ProjetListActivity;
import com.sharpinfo.sir.gestfly.action.rapport.RapportListActivity;
import com.sharpinfo.sir.gestfly.action.salaire.SalaireListActivity;
import com.sharpinfo.sir.gestfly.action.tache.TacheListActivity;
import com.sharpinfo.sir.gestfly.action.user.LoginActivity;
import com.sharpinfo.sir.gestfly.helper.Dispacher;
import com.sharpinfo.sir.gestfly.helper.Session;

public class MenuTechnicienActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView projetCard;
    private CardView tacheCard;
    private CardView congeCard;
    private CardView salaireCard;
    private CardView rapportCard;
    private CardView parametreCard;
    private Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_technicien);

        projetCard = findViewById(R.id.projetCardView);
        tacheCard = findViewById(R.id.tacheCardView);
        congeCard = findViewById(R.id.congeCardView);
        salaireCard = findViewById(R.id.salaireCardView);
        rapportCard = findViewById(R.id.rapprtCardView);

        projetCard.setOnClickListener(this);
        tacheCard.setOnClickListener(this);
        congeCard.setOnClickListener(this);
        salaireCard.setOnClickListener(this);
        rapportCard.setOnClickListener(this);

        menu = findViewById(R.id.settings_icon);
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
            case R.id.rapprtCardView:
                Dispacher.forward(MenuTechnicienActivity.this, RapportListActivity.class);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.settings_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.profile_option:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.change_password_option:
                Toast.makeText(this, "Change password", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.sign_out_option:
                Dispacher.forward(MenuTechnicienActivity.this, LoginActivity.class);
                Session.delete("connectedUser");
                finish();
                break;
            default:
                break;
        }
        return true;
    }

}
