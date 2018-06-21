package com.sharpinfo.sir.gestfly.reftroFitApi;

import com.sharpinfo.sir.gestfly.bean.Conge;
import com.sharpinfo.sir.gestfly.bean.DemandeSalaire;
import com.sharpinfo.sir.gestfly.bean.Image;
import com.sharpinfo.sir.gestfly.bean.Projet;
import com.sharpinfo.sir.gestfly.bean.Rapport;
import com.sharpinfo.sir.gestfly.bean.Tache;
import com.sharpinfo.sir.gestfly.bean.TypeDemandeSalaire;
import com.sharpinfo.sir.gestfly.bean.TypeEtatDemande;
import com.sharpinfo.sir.gestfly.bean.User;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
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

    //Insert rapport
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

    //Insert journee
    @POST("journee")
    @FormUrlEncoded
    Call<Integer> createJournee(
            @Field("date_debut") String date_debut,
            @Field("date_fin") String date_fin,
            @Field("temps_debut") Long temps_debut,
            @Field("temps_fin") Long temps_fin,
            @Field("duree") Long duree,
            @Field("user_id") Long user_id
    );

    //Insert travail_projet
    @POST("travailprojet")
    @FormUrlEncoded
    Call<Integer> createTravailProjet(
            @Field("date_debut") String date_debut,
            @Field("date_fin") String date_fin,
            @Field("temps_debut") Long temps_debut,
            @Field("temps_fin") Long temps_fin,
            @Field("duree") Long duree,
            @Field("user_id") Long user_id,
            @Field("projet_id") Long projet_id
    );


    /***Find User***/
//    @GET("user/{id]")
////    Call<User> findUser(@Path("id") Long id);
//    Call<User> findUser(@Path("id") Long id);
    @GET("user/{id}")
    Call<User> findUser(@Path("id") Long id);

    /***Find ProjetBy ID***/
    @GET("projet/{id}")
    Call<Projet> findProjet(@Path("id") Long id);

    /***Find TacheBy ID***/
    @GET("tache/{id}")
    Call<Tache> findTache(@Path("id") Long id);


    /***FIND BY user**/
    @GET("projet/user/{id}")
    Call<List<Projet>> getProjetsByUser(@Path("id") Long id);

    @GET("rapport/user/{id}")
    Call<List<Rapport>> getRapportsByUser(@Path("id") Long id);

    @GET("tache/user/{id}")
    Call<List<Tache>> getTachesByUser(@Path("id") Long id);

    @GET("conge/user/{id}")
    Call<List<Conge>> getCongesByUser(@Path("id") Long id);

    @GET("demandesalaire/user/{id}")
    Call<List<DemandeSalaire>> getDemandesSalaireByUser(@Path("id") Long id);


    /*********DELETE***/
    @DELETE("conge/{id}")
    Call<Integer> deleteConge(@Path("id") Long id);

    @DELETE("demandeSalaire/{id}")
    Call<Integer> deleteDemandeSalaire(@Path("id") Long id);
}
