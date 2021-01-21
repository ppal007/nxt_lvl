package com.ppal007.nxtlvl.myDb;

import androidx.room.Insert;
import androidx.room.Query;

@androidx.room.Dao
public interface Dao {

    //register
    @Insert
    long user_insertion(UserEntity userEntity);

    //login
    @Query("select * from UserEntity where userName like :userName AND password like :pass")
    UserEntity login_user(String userName, String pass);


}
