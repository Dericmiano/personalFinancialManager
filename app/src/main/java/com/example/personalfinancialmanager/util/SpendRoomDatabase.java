package com.example.personalfinancialmanager.util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.personalfinancialmanager.data.SpendDao;
import com.example.personalfinancialmanager.model.Spend;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Spend.class}, version = 1,exportSchema = false)
@TypeConverters({Converter.class})
public abstract class SpendRoomDatabase extends RoomDatabase {
    public static final int NUMBER_OF_THREADS = 4;
    public static final String DATABASE_NAME = "spenderDatabase";
    public static volatile SpendRoomDatabase INSTANCE;
    public static final ExecutorService
            databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    public static final RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);
                    databaseWriteExecutor.execute(() ->{
                        //invoke dao and write
                        SpendDao spendDao = INSTANCE.spendDao();
                        spendDao.deleteAll();


                    });
                }
            };
    public static SpendRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (SpendRoomDatabase.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SpendRoomDatabase.class,DATABASE_NAME)
                            .addCallback(sRoomDatabaseCallback)
                            .build();

                }
            }
        }
        return INSTANCE;
    }
    public abstract SpendDao spendDao();

}
