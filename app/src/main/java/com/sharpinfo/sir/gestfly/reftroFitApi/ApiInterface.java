package com.sharpinfo.sir.gestfly.reftroFitApi;

import com.sharpinfo.sir.gestfly.bean.Image;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("user/login")
    @FormUrlEncoded
    Call<User> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("upload.php")
    Call<Image> uploadImage(@Field("title") String title, @Field("image") String image);


    @GET("projets")
    Call<List<Projet>> getAllProjets();

    @GET("projet/user/{id}")
    Call<List<Projet>> getProjetsByUser(@Path("id") Long id);

    @GET("tache/user/{id}")
    Call<List<Tache>> getTachesByUser(@Path("id") Long id);
}
