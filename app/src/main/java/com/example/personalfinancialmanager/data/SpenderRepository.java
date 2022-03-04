package com.example.personalfinancialmanager.data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.personalfinancialmanager.model.Spend;
import com.example.personalfinancialmanager.util.SpendRoomDatabase;

import java.util.List;

public class SpenderRepository {
    private final SpendDao spendDao;
    private final LiveData<List<Spend>> allSpends;
    public SpenderRepository(Application application) {
        SpendRoomDatabase database = SpendRoomDatabase.getDatabase(application);
        spendDao = database.spendDao();
        allSpends = spendDao.getSpends();
    }
    public LiveData<List<Spend>> getAllSpends(){return allSpends;}

    public void insert(Spend spend){
        SpendRoomDatabase.databaseWriteExecutor.execute(()->
                spendDao.insertSpend(spend));

    }
    public LiveData<Spend> get(long id){return spendDao.get(id);}
    public void update(Spend spend){
        SpendRoomDatabase.databaseWriteExecutor.execute(()->
                spendDao.update(spend));

    }
    public void delete(Spend spend){
        SpendRoomDatabase.databaseWriteExecutor.execute(()->
                spendDao.delete(spend));
    }
}
