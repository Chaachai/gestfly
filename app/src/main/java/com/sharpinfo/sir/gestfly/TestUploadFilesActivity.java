package com.sharpinfo.sir.gestfly;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sharpinfo.sir.gestfly.helper.FileUtils;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiClient;
import com.sharpinfo.sir.gestfly.reftroFitApi.ApiInterface;


import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestUploadFilesActivity extends AppCompatActivity {

//    Service service;

    public static final int PICK_IMAGE = 100;
    ProgressDialog dialog;
    private String selectedFilePath;
    private Uri selectedFileUri;
    TextView tvFileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_upload_files);

        Button btn = findViewById(R.id.btn_upload);
        Button btnUpload = findViewById(R.id.btn_choose);
        tvFileName = findViewById(R.id.tvFileName);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedFilePath != null) {
                    dialog = ProgressDialog.show(TestUploadFilesActivity.this, "", "Uploading File...", true);
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            //creating new thread to handle Http Operations
                            uploadFile(selectedFileUri);
                        }
                    }).start();
                }
            }
        });

//        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
//        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

//        service = new Retrofit.Builder().baseUrl("http://192.168.0.234:3000").client(client).build().create(Service.class);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE) {
                if (data == null) {
                    //no data present
                    return;
                }

                selectedFileUri = data.getData();
                selectedFilePath = FileUtils.getPath(this, selectedFileUri);
                Log.i("tag", "Selected File Path:" + selectedFilePath);

                if (selectedFilePath != null && !selectedFilePath.equals("")) {
                    tvFileName.setText(selectedFilePath);
                } else {
                    Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private void uploadFile(Uri fileUri) {
        Log.v("Upload", "Hani d5alt l uploaaad");
        // create upload service client
//        FileUploadService service =
//                ServiceGenerator.createService(FileUploadService.class);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        // https://github.com/iPaulPro/aFileChooser/blob/master/aFileChooser/src/com/ipaulpro/afilechooser/utils/FileUtils.java
        // use the FileUtils to get the actual file by uri
        File file = FileUtils.getFile(this, fileUri);
        Log.v("Upload", "File ====== "+file.getName());
        // create RequestBody instance from file
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(getContentResolver().getType(fileUri)),
                        file
                );

        Log.v("Upload", "RequestFile ====== "+requestFile.toString());

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("picture", file.getName(), requestFile);

        // add another part within the multipart request
        String descriptionString = "hello, this is description speaking";
        RequestBody description =
                RequestBody.create(
                        okhttp3.MultipartBody.FORM, descriptionString);
        Log.v("Upload", "Description ====== "+description.toString());
        Log.v("Upload", "Body ====== "+body.toString());

        // finally, execute the request
        Call<ResponseBody> call = apiInterface.upload(description, body);
        Log.v("Upload", "Call ====== "+call.toString());
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                Log.v("Upload", "success");
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("Upload error:", t.getMessage());
                dialog.dismiss();
            }
        });
    }


}
