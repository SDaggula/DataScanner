package com.eastwest.message.cardread.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

/**
 * Created by SreeL1 on 10/27/2018.
 */

public class ChequeViewModel extends AndroidViewModel {

    private ChequeRepository mRepository;

    private LiveData<List<Cheque>> mAllCheques;

    public ChequeViewModel (Application application) {
        super(application);
        mRepository = new ChequeRepository(application);
        mAllCheques = mRepository.getAllCheques();
    }

    public LiveData<List<Cheque>> getAllCheques() { return mAllCheques; }

    public void insert(Cheque cheque) { mRepository.insert(cheque); }

    public void delete(Cheque cheque) { mRepository.delete(cheque); }
}