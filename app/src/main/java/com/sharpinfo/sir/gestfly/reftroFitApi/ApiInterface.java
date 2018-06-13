package com.sharpinfo.sir.gestfly.reftroFitApi;

import com.sharpinfo.sir.gestfly.bean.Conge;
import com.sharpinfo.sir.gestfly.bean.DemandeSalaire;
import com.sharpinfo.sir.gestfly.bean.Image;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.bean.TypeDemandeSalaire;
import com.sharpinfo.sir.gestfly.bean.TypeEtatDemande;
import com.sharpinfo.sir.gestfly.bean.User;

import java.math.BigDecimal;
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
    Call<Image> uploadImage(@Field("title") String title, @Field("image") String image);

    /**** find ALL***/
    @GET("projets")
    Call<List<Projet>> getAllProjets();

    @GET("typeEtatDemandes")
    Call<List<TypeEtatDemande>> getAllTypeEtatDemandes();

    @GET("typeDemandeSalaire")
    Call<List<TypeDemandeSalaire>> getAllTypeDemandeSalaire();

    /******Insert*****/
    @POST("conge")
    @FormUrlEncoded
    Call<Integer> createConge(
            @Field("datedebut") String datedebut,
            @Field("datereprise") String datereprise,
            @Field("user_id") Long user_id
    );

    @POST("demandeSalaireAvance")
    @FormUrlEncoded
    Call<Integer> createDemandeSalaireAvance(
            @Field("date") String date,
            @Field("message") String message,
            @Field("moisavancer") Integer moisAvancer,
            @Field("user_id") Long user_id
    );

    @POST("demandeSalaireAugmentation")
    @FormUrlEncoded
    Call<Integer> createDemandeSalaireAugmentation(
            @Field("date") String date,
            @Field("message") String message,
            @Field("montantajouter") BigDecimal montantajouter,
            @Field("salaireactuel") BigDecimal salaireActuel,
            @Field("user_id") Long user_id
    );


    /***Find User***/
//    @GET("user/{id]")
////    Call<User> findUser(@Path("id") Long id);
//    Call<User> findUser(@Path("id") Long id);
    @GET("user/{id}")
    Call<User> findUser(@Path("id") Long id);

    /***FIND BY user**/
    @GET("projet/user/{id}")
    Call<List<Projet>> getProjetsByUser(@Path("id") Long id);

    @GET("tache/user/{id}")
    Call<List<Tache>> getTachesByUser(@Path("id") Long id);

    @GET("conge/user/{id}")
    Call<List<Conge>> getCongesByUser(@Path("id") Long id);

    @GET("demandesalaire/user/{id}")
    Call<List<DemandeSalaire>> getDemandesSalaireByUser(@Path("id") Long id);
}
