package com.eastwest.message.cardread.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by SreeL1 on 10/27/2018.
 */

public class ChequeRepository {
    private ChequeDao mChequeDuo;
    private LiveData<List<Cheque>> mAllCheques;

    ChequeRepository(Application application) {
        AppDatabase db = AppDatabase.getAppDatabase(application);
        mChequeDuo = db.chequeDuo();
        mAllCheques = mChequeDuo.getAll();
    }

    LiveData<List<Cheque>> getAllCheques() {
        return mAllCheques;
    }


    public void insert (Cheque word) {
        new insertAsyncTask(mChequeDuo).execute(word);
    }

    public void delete (Cheque word) {
        new deleteAsyncTask(mChequeDuo).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Cheque, Void, Void> {

        private ChequeDao mAsyncTaskDao;

        insertAsyncTask(ChequeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Cheque... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class deleteAsyncTask extends AsyncTask<Cheque, Void, Void> {

        private ChequeDao mAsyncTaskDao;

        deleteAsyncTask(ChequeDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Cheque... params) {
            mAsyncTaskDao.delete(params[0]);
            return null;
        }
    }
}
