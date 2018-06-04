package com.sharpinfo.sir.gestfly.dao.helper;

import android.provider.BaseColumns;

public final class DbStructure {

    public static final String dbName = "gestfly";
    public static final int DB_VERSION = 1;

    public static abstract class User implements BaseColumns {

        public static final String T_NAME = "user";
        public static final String C_ID = "id";
        public static final String C_USERNAME = "username";
        public static final String C_PASSWORD = "password";
        public static final String C_LASTNAME = "last_name";
        public static final String C_FIRSTNAME = "first_name";
        public static final String C_EMAIL = "email";
        public static final String C_ADRESSE = "adresse";
        public static final String C_PHONE = "phone";
        public static final String C_ZIPCODE = "zip_code";
        public static final String C_IMAGE = "image";
        public static final String C_BLOCKED = "blocked";
        public static final String C_LASTLOGIN = "last_login";
        public static final String C_PASSWORDREQUESTEDAT = "password_requested_at";
        public static final String C_NBRCONNECTION = "nbr_connection";
        public static final String C_ID_ROLE = "role_id";
        public static final String C_ID_VILLE = "ville_id";
        public static final String C_ID_JOB = "job_id";
        public static final String SQL_CREATE = "create table " + T_NAME + "("
                + C_ID + " INTEGER PRIMARY KEY, "
                + C_USERNAME + " TEXT, "
                + C_PASSWORD + " TEXT, "
                + C_LASTNAME + " TEXT, "
                + C_FIRSTNAME + " TEXT, "
                + C_EMAIL + " TEXT, "
                + C_ADRESSE + " TEXT, "
                + C_PHONE + " TEXT, "
                + C_ZIPCODE + " TEXT, "
                + C_IMAGE + " TEXT, "
                + C_BLOCKED + " BOOLEAN, "
                + C_LASTLOGIN + " DATE, "
                + C_PASSWORDREQUESTEDAT + " DATE, "
                + C_NBRCONNECTION + " INTEGER, "
                + C_ID_ROLE + " INTEGER REFERENCES " + Role.T_NAME + "( " + Role.C_ID + " ), "
                + C_ID_VILLE + " INTEGER REFERENCES " + Ville.T_NAME + "( " + Ville.C_ID + " ), "
                + C_ID_JOB + " INTEGER REFERENCES " + Job.T_NAME + "( " + Job.C_ID + " ) ";
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + T_NAME;
    }

    public static abstract class Role implements BaseColumns {

        public static final String T_NAME = "role";
        public static final String C_ID = "id";
        public static final String C_LIBELLE = "libelle";

        public static final String SQL_CREATE = "create table " + T_NAME + "("
                + C_ID + " INTEGER PRIMARY KEY NOT NULL, "
                + C_LIBELLE + " TEXT )";
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + T_NAME;
    }

    public static abstract class Ville implements BaseColumns {

        public static final String T_NAME = "ville";
        public static final String C_ID = "id";
        public static final String C_NOM = "nom";

        public static final String SQL_CREATE = "create table " + T_NAME + "("
                + C_ID + " INTEGER PRIMARY KEY NOT NULL, "
                + C_NOM + " TEXT )";
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + T_NAME;
    }

    public static abstract class Job implements BaseColumns {

        public static final String T_NAME = "job";
        public static final String C_ID = "id";
        public static final String C_NOM = "nom";

        public static final String SQL_CREATE = "create table " + T_NAME + "("
                + C_ID + " INTEGER PRIMARY KEY NOT NULL, "
                + C_NOM + " TEXT )";
        public static final String SQL_DROP = "DROP TABLE IF EXISTS " + T_NAME;
    }


}
