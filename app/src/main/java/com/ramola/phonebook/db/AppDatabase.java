package com.ramola.phonebook.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ramola.phonebook.db.dao.ContactDao;
import com.ramola.phonebook.db.entity.ContactEntity;

@Database(entities = {ContactEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase database;
    public static final String DATABASE_NAME = "basic-sample-db";

    public static AppDatabase getInstance(Context context){
        if (database == null) {
            synchronized (AppDatabase.class) {
                if (database == null) {
                    database = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                                .build();
                }
            }
        }
        return database;
    }

    public abstract ContactDao contactDao();
}
