package com.sharpinfo.sir.gestfly;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.helper.Session;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClientForImage;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestUploadFilesActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText Img_title;
    private Button BnChoose, BnUpload;
    private ImageView Img;
    private static final int IMG_REQUEST = 777;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_upload_files);

        Img_title = findViewById(R.id.img_title);
        BnChoose = findViewById(R.id.chooseBn);
        BnUpload = findViewById(R.id.uploadBn);
        Img = findViewById(R.id.imageView);

        BnUpload.setOnClickListener(this);
        BnChoose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.uploadBn:
//                uploadImage();
                break;
            case R.id.chooseBn:
                selectImage();
                break;
        }
    }

//    private void uploadImage() {
//        String Image = imageToString();
//        Random r = new Random();
//        int i1 = r.nextInt(35789 - 15987) + 35789;
//        long time= System.currentTimeMillis();
//        String Title = "Img_"+time+"_"+i1;
//        ApiInterface apiInterface = ApiClientForImage.getApiClient().create(ApiInterface.class);
//        Call<com.sharpinfo.sir.gestfly.bean.Image> call = apiInterface.uploadImage(Title, Image);
//
//        call.enqueue(new Callback<com.sharpinfo.sir.gestfly.bean.Image>() {
//            @SuppressLint("ShowToast")
//            @Override
//            public void onResponse(Call<com.sharpinfo.sir.gestfly.bean.Image> call, Response<com.sharpinfo.sir.gestfly.bean.Image> response) {
//                com.sharpinfo.sir.gestfly.bean.Image image = response.body();
//                Toast.makeText(TestUploadFilesActivity.this, "Server Response : " + image.getResponse(), Toast.LENGTH_LONG).show();
//                Log.d("tag", "SERVER RESPOOOOOOOONSE = "+ image.getResponse());
//                Img.setVisibility(View.GONE);
//                Img_title.setVisibility(View.GONE);
//                BnChoose.setEnabled(true);
//                BnUpload.setEnabled(false);
//                Img_title.setText("");
//
//            }
//
//            @Override
//            public void onFailure(Call<com.sharpinfo.sir.gestfly.bean.Image> call, Throwable t) {
//                Log.d("tag", "FUCK OOOOOOOOFFFF");
//            }
//        });
//    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
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
                Img_title.setVisibility(View.VISIBLE);
                BnChoose.setEnabled(false);
                BnUpload.setEnabled(true);
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

}
