package com.sharpinfo.sir.gestfly.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.sharpinfo.sir.gestfly.bean.User;
import com.sharpinfo.sir.gestfly.dao.helper.AbstractDao;
import com.sharpinfo.sir.gestfly.dao.helper.DbStructure;

import java.util.Date;


public class UserDao extends AbstractDao<User> {

    @Override
    public long create(User user) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbStructure.User.C_ID, user.getId());
        contentValues.put(DbStructure.User.C_PASSWORD, user.getPassword());
        contentValues.put(DbStructure.User.C_LASTNAME, user.getLastName());
        contentValues.put(DbStructure.User.C_FIRSTNAME, user.getFirstName());
        contentValues.put(DbStructure.User.C_NBRCONNECTION, user.getNbrConnection());
        return getDb().insert(DbStructure.User.T_NAME, null, contentValues);
    }

    @Override
    public long edit(User user) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DbStructure.User.C_ID, user.getId());
        contentValues.put(DbStructure.User.C_PASSWORD, user.getPassword());
        contentValues.put(DbStructure.User.C_LASTNAME, user.getLastName());
        contentValues.put(DbStructure.User.C_FIRSTNAME, user.getFirstName());
        contentValues.put(DbStructure.User.C_NBRCONNECTION, user.getNbrConnection());
        return db.update(DbStructure.User.T_NAME, contentValues, DbStructure.User.C_ID + " = '" + user.getId() + "'", null);
    }

    public long remove(User user) {
        open();
        return db.delete(DbStructure.User.T_NAME, DbStructure.User.C_ID + "=" + user.getId(), null);
    }

    protected User transformeCursorToBean(Cursor cursor) {
        Long date1FromCursor = cursor.getLong(cursor.getColumnIndex(DbStructure.User.C_LASTLOGIN));
        Long date2FromCursor = cursor.getLong(cursor.getColumnIndex(DbStructure.User.C_PASSWORDREQUESTEDAT));

        Date lastLogin = new Date(date1FromCursor);
        Date passwordRequestedAt = new Date(date2FromCursor);

        boolean blocked = cursor.getInt(cursor.getColumnIndex(DbStructure.User.C_BLOCKED)) > 0;

        return new User(
                cursor.getString(cursor.getColumnIndex(DbStructure.User.C_ID)),
                cursor.getString(cursor.getColumnIndex(DbStructure.User.C_PASSWORD)),
                cursor.getString(cursor.getColumnIndex(DbStructure.User.C_LASTNAME)),
                cursor.getString(cursor.getColumnIndex(DbStructure.User.C_FIRSTNAME)),
                cursor.getString(cursor.getColumnIndex(DbStructure.User.C_EMAIL)),
                cursor.getString(cursor.getColumnIndex(DbStructure.User.C_ADRESSE)),
                cursor.getString(cursor.getColumnIndex(DbStructure.User.C_PHONE)),
                cursor.getString(cursor.getColumnIndex(DbStructure.User.C_ZIPCODE)),
                cursor.getString(cursor.getColumnIndex(DbStructure.User.C_IMAGE)),
                blocked,
                lastLogin,
                passwordRequestedAt,
                cursor.getInt(cursor.getColumnIndex(DbStructure.User.C_NBRCONNECTION)),
                cursor.getLong(cursor.getColumnIndex(DbStructure.User.C_ID_ROLE)),
                cursor.getLong(cursor.getColumnIndex(DbStructure.User.C_ID_VILLE)),
                cursor.getLong(cursor.getColumnIndex(DbStructure.User.C_ID_JOB))
        );
    }

    public UserDao(Context context) {
        super(context);
        columns = new String[]{
                DbStructure.User.C_ID,
                DbStructure.User.C_PASSWORD,
                DbStructure.User.C_LASTNAME,
                DbStructure.User.C_FIRSTNAME,
                DbStructure.User.C_EMAIL,
                DbStructure.User.C_ADRESSE,
                DbStructure.User.C_PHONE,
                DbStructure.User.C_ZIPCODE,
                DbStructure.User.C_IMAGE,
                DbStructure.User.C_BLOCKED,
                DbStructure.User.C_LASTLOGIN,
                DbStructure.User.C_PASSWORDREQUESTEDAT,
                DbStructure.User.C_NBRCONNECTION,
                DbStructure.User.C_ID_ROLE,
                DbStructure.User.C_ID_VILLE,
                DbStructure.User.C_ID_JOB,
        };
        tableName = DbStructure.User.T_NAME;
        idName = DbStructure.User.C_ID;
    }

}