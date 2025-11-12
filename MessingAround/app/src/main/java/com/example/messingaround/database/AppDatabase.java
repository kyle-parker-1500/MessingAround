package com.example.messingaround.database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.messingaround.MainActivity;
import com.example.messingaround.database.entities.User;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    // for room to handle
    public abstract UserDao userDao();
    public static final String USER_TABLE = "userTable";
    private static final String DATABASE_NAME = "AppDatabase";
    // names of tables go here
    public static final String GYM_LOG_TABLE = "otherTable";

    // volatile -> only lives in ram
    private static volatile AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4; // multithreading!!!

    // says: create service to supply threads
    // create @ startup and put in pool -> DB will have max of 4 threads
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    // singleton access for database
    static AppDatabase getDatabase(final Context context) {
        // INSTANCE not obj -> don't need .equals()
        if (INSTANCE == null) {
            // locks stuff into single thread
            // makes sure nothing is referencing our class (only want one instance of db)
            synchronized (AppDatabase.class) {
                // .fallbackToDestructiveMigration() -> basically helps us if version changes... I think (eg. oh.. I've made a mistake & app doesn't crash)
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    DATABASE_NAME
                            )
                            .fallbackToDestructiveMigration()
                            .addCallback(addDefaultValues)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    // .addCallback() does:
    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.i(MainActivity.TAG, "DATABASE CREATED!");
            databaseWriteExecutor.execute(() -> {
                UserDao dao = INSTANCE.userDao();
                dao.deleteAll();
                User testUser1 = new User("testuser1", "testuser1");
                dao.insert(testUser1);
            });
        }
    };
}
