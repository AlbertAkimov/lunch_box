package com.example.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.model.User;
import com.example.repository.UserRepository;

/**
 * @Authot: Albert Akimov
 * @Date: 24.10.2021
 * @Description:
 */

@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserRepository userRepository();

    private static AppDatabase INSTANCE;

    public static AppDatabase getInstance(Context context) {

        if(INSTANCE == null)
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "my_database")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();

        return INSTANCE;
    }
}
