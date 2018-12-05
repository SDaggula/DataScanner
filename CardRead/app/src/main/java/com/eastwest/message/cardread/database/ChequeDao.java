package com.eastwest.message.cardread.database;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import java.util.List;
/**
 * Created by SreeL1 on 10/27/2018.
 */

@Dao
public interface ChequeDao {

    @Query("SELECT * FROM cheque")
    LiveData<List<Cheque>> getAll();

    @Query("SELECT * FROM cheque where amount LIKE  :amount")
    Cheque findByAmount(String amount);

    @Query("SELECT * FROM cheque where date LIKE  :date")
    Cheque findByDate(String date);

    @Query("SELECT COUNT(*) from cheque")
    int countCheques();

    @Insert
    void insertAll(Cheque... cheques);

    @Delete
    void delete(Cheque cheque);

    @Insert
    void insert(Cheque cheque);
}

