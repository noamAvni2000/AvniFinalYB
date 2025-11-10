package com.example.avnifinalyb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsernamesDao {
    @Insert
    void insert(Usernames u);

    @Update
    void update (Usernames u);

    @Delete
    void delete (Usernames u);

    @Query("SELECT * FROM Usernames WHERE username = :username AND password = :password")
    Usernames login(String username, String password);

    @Query("SELECT*FROM Usernames")
    List<Usernames>getAllInfo();

}
