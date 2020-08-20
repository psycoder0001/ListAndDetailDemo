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
@Database(entities = {PersonModel.class}, version = 1)
public abstract class LoanDataBase extends RoomDatabase {

    private static LoanDataBase INSTANCE;

    public abstract LoanDao loanDao();

    public static LoanDataBase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (LoanDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room
                            .databaseBuilder(context.getApplicationContext(), LoanDataBase.class, "LoanDB")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
