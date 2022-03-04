package com.example.personalfinancialmanager.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.personalfinancialmanager.model.Spend;

import java.util.List;

@Dao
public interface SpendDao {
    @Insert
    void insertSpend(Spend spend);

    @Query("DELETE FROM spend_table")
    void deleteAll();

    @Query("SELECT * FROM spend_table")
    LiveData<List<Spend>> getSpends();

    @Query("SELECT * FROM spend_table WHERE spend_table.spend_id ==:id")
    LiveData<Spend> get(long id);

    @Update
    void update(Spend spend);

    @Delete
    void delete(Spend spend);




}
