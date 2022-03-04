package com.example.personalfinancialmanager.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.personalfinancialmanager.data.SpenderRepository;

import java.util.List;

public class SpendViewModel extends AndroidViewModel {
    public static SpenderRepository repository;
    public final LiveData<List<Spend>> allSpends;
    public SpendViewModel(@NonNull Application application) {
        super(application);
        repository = new SpenderRepository(application);
        allSpends =repository.getAllSpends();
    }
    public LiveData<List<Spend>> getAllSpends(){return allSpends;}
    public static void insert(Spend spend){repository.insert(spend);}
    public LiveData<Spend> get(long id){return repository.get(id);}
    public static void update(Spend spend){repository.update(spend);}
    public static void delete(Spend spend){repository.delete(spend);}
}
