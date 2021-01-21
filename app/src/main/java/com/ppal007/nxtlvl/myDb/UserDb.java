package com.ppal007.nxtlvl.myDb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {UserEntity.class},version = 1, exportSchema = false)
public abstract class UserDb extends RoomDatabase {

    public static  UserDb db;
    public abstract Dao getDaoInstance();
    public static UserDb getUserDbInstance(Context context){
        if(db==null){

            db= Room.databaseBuilder(context, UserDb.class, "userInfo")
                    .allowMainThreadQueries()
                    .build();

        }
        return db;
    }

}
