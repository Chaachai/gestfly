package com.sharpinfo.sir.gestfly.action.rapport;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.R;
import com.sharpinfo.sir.gestfly.TestUploadFilesActivity;
import com.sharpinfo.sir.gestfly.adapter.ProjetSpinnerAdapter;
import com.sharpinfo.sir.gestfly.bean.Image;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClientForImage;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreerRapportActivity extends AppCompatActivity implements View.OnClickListener {

    private Button BtnChoose, BtnSend;
    private ImageView Img;
    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;
    private Spinner projetSpinner;
    private Projet projet = null;
    private ProjetSpinnerAdapter projetSpinnerAdapter;
    Integer intRes = 0;
    Integer rapportFlag;
    Long idUser = ((User) Session.getAttribut("connectedUser")).getId();

    // ************************************************ PROJET SPINNER **********************************************************


    private void initProjetSpinner() {
        projetSpinner = findViewById(R.id.spinner_projet_tache);
        List<Projet> projets = new ArrayList<>();
        projetSpinnerAdapter = new ProjetSpinnerAdapter(this, android.R.layout.simple_spinner_item, projets);
        projetSpinnerAdapter.add(new Projet(null, " ---Séléctionner--- "));
        projetSpinner.setAdapter(projetSpinnerAdapter);
        projetSpinnerAdapter.notifyDataSetChanged();
        projetSpinner.setSelection(projetSpinnerAdapter.getCount() + 1, true);
    }

    private Projet getProjetFromSpinner() {
        projetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                projet = projetSpinnerAdapter.getItem(position);
                if (projet.getId() == null) {
                    projet = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return projet;
    }

    // **************************************************************************************************************************

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_rapport);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        TextView label = findViewById(R.id.spinner_label_rapport);
        BtnChoose = findViewById(R.id.upload_image_rapport);
        BtnSend = findViewById(R.id.envoyer_rapport);
        Img = findViewById(R.id.image_rapport);

        BtnChoose.setOnClickListener(this);
        BtnSend.setOnClickListener(this);

        rapportFlag = (Integer) Session.getAttribut("rapportFlag");
        if (rapportFlag == 1) {
            label.setText("Choisissez un projet :");
        } else if (rapportFlag == 2) {
            label.setText("Choisissez une tâche :");
        } else {
            label.setText("");
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.upload_image_rapport:
                selectImage();
                break;
            case R.id.envoyer_rapport:
                uploadImage();
                break;
        }
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    private void uploadImage() {
        String ImageString = imageToString();
        Random r = new Random();
        int i1 = r.nextInt(35789 - 15987) + 35789;
        long time = System.currentTimeMillis();
        String Title = "IMG_" + time + "_" + i1;
        ApiInterface apiInterface = ApiClientForImage.getApiClient().create(ApiInterface.class);
        Call<Integer> call = apiInterface.uploadImage(Title, ImageString);

        call.enqueue(new Callback<Integer>() {
            @SuppressLint("ShowToast")
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
//                Image image = response.body();
                intRes = response.body();
                Toast.makeText(CreerRapportActivity.this, "Server Response : " + intRes, Toast.LENGTH_LONG).show();
                Log.d("tag", "SERVER RESPOOOOOOOONSE = " + intRes);
                Session.updateAttribute(intRes, "id_image");
                Img.setVisibility(View.GONE);
                createRapport();
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                Log.d("tag", "failure");
                t.printStackTrace();
                Log.d("tag", t.toString());
                Log.d("tag", "FUCK OOOOOOOOFFFF");
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                Img.setImageBitmap(bitmap);
                Img.setVisibility(View.VISIBLE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String imageToString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imageByte = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imageByte, Base64.DEFAULT);
    }

    private void createRapport() {
        EditText titre = findViewById(R.id.titr_rapport_create);
        EditText text = findViewById(R.id.text_rapport_create);

        Integer id_image = (Integer) Session.getAttribut("id_image");
        Log.d("tag", "Image id ===== " + id_image);
        Log.d("tag", "User id ===== " + idUser);
        Log.d("tag", "projet ===== " + projet.getNom());
//        Log.d("tag", "projet ===== " + projet.getNom());
    }

}
