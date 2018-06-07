package com.sharpinfo.sir.gestfly.reftroFitApi;

import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    @POST("user/login")
    @FormUrlEncoded
    Call<User> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @GET("projets")
    Call<List<Projet>> getAllProjets();

    @GET("projet/user/{id}")
    Call<List<Projet>> getProjetsByUser(@Path("id") Long id);
}
