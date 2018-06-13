package com.sharpinfo.sir.gestfly.reftroFitApi;

import com.sharpinfo.sir.gestfly.bean.Conge;
import com.sharpinfo.sir.gestfly.bean.Image;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Rapport;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.bean.TypeEtatDemande;
import com.sharpinfo.sir.gestfly.bean.User;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<Integer> uploadImage(@Field("title") String title, @Field("image") String image);

    /**** find ALL***/
    @GET("projets")
    Call<List<Projet>> getAllProjets();

    @GET("typeEtatDemandes")
    Call<List<TypeEtatDemande>> getAllTypeEtatDemandes();

    /******Insert*****/
    @POST("conge")
    @FormUrlEncoded
    Call<Integer> createConge(
            @Field("datedebut") String datedebut,
            @Field("datereprise") String datereprise,
            @Field("user_id") Long user_id
    );

    @POST("rapport")
    @FormUrlEncoded
    Call<Integer> createRapport(
            @Field("date") String date,
            @Field("projet_id") Long projet_id,
            @Field("text") String text,
            @Field("title") String title,
            @Field("tache_id") Long tache_id,
            @Field("user_id") Long user_id,
            @Field("image_id") Integer image_id
    );



    /***FIND BY user**/
    @GET("projet/user/{id}")
    Call<List<Projet>> getProjetsByUser(@Path("id") Long id);

    @GET("rapport/user/{id}")
    Call<List<Rapport>> getRapportsByUser(@Path("id") Long id);

    @GET("tache/user/{id}")
    Call<List<Tache>> getTachesByUser(@Path("id") Long id);

    @GET("conge/user/{id}")
    Call<List<Conge>> getCongesByUser(@Path("id") Long id);
}
