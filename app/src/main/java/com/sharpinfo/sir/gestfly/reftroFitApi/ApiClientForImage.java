package com.sharpinfo.sir.gestfly.reftroFitApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientForImage {


    private static final String BASE_URL = "http://10.0.3.2/Gestfly/gestfly/imageupload/";
//    public static Retrofit retrofit = null;


    public static Retrofit getApiClient() {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
//
//        }
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create());

        return builder.client(httpClient.build()).build();
    }
}
