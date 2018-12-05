package com.eastwest.message.cardread.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by SreeL1 on 10/27/2018.
 */

@Entity(tableName = "cheque")
public class Cheque {

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name = "amount")
    private String amount;

    @ColumnInfo(name = "date")
    private String date;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
