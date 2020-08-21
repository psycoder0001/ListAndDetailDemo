package com.ewo.laddemo.localdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * This is the local db(SQLite) instance management class.
 * Creating different db instances are too expensive & a performans killer.
 * So we create an instance at first need & keep it here.
 * <p>
 * If the db instance is lost somehow due to lack of memory etc.
 * a fresh new instance will be created automatically & no crash will appear.
 * <p>
 * In this this case we have only one db & it uses a single table named PersonModel.
 */
@Database(entities = {MovieModel.class}, version = 1)
public abstract class MovieDataBase extends RoomDatabase {

    private static MovieDataBase INSTANCE;

    public abstract MovieDao movieDao();

    public static MovieDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), MovieDataBase.class, "LoanDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
