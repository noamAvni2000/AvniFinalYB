package com.example.avnifinalyb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Usernames.class}, version = 5)
public abstract class UsersDatabase extends RoomDatabase {
    private static UsersDatabase instance;

    public abstract UsernamesDao usernamesDao();

    public static synchronized UsersDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            UsersDatabase.class,
                            "users_database"
                    )
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

