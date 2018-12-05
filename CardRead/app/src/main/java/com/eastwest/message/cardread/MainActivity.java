package com.eastwest.message.cardread;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.Toast;

import com.eastwest.message.cardread.database.Cheque;
import com.eastwest.message.cardread.database.ChequeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private ChequeRecyclerViewAdapter adapter;
//    private ArrayList<Cheque> chequeDataList = new ArrayList<>();

    private ChequeViewModel mChequeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initChequeViewModel();
    }

    private void initRecyclerView(){
        RecyclerView recyclerView = findViewById(R.id.chequeDataView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        adapter = new ChequeRecyclerViewAdapter(this);

        recyclerView.setLayoutManager(layoutManager);
        itemTouchHelper.attachToRecyclerView(recyclerView);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adapter);
    }

    private void initChequeViewModel(){
        mChequeViewModel = ViewModelProviders.of(this).get(ChequeViewModel.class);

        mChequeViewModel.getAllCheques().observe(this, new Observer<List<Cheque>>() {
            @Override
            public void onChanged(@Nullable final List<Cheque> cheques) {
                // Update the cached copy of the words in the adapter.
                adapter.setCheques(cheques);
            }
        });
    }

    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP) {

        @Override
        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
            Toast.makeText(MainActivity.this, "Cheque data deleted", Toast.LENGTH_SHORT).show();
            //Remove swiped item from list and notify the RecyclerView
            int position = viewHolder.getAdapterPosition();
            mChequeViewModel.delete(adapter.getItem(position));
        }
    };
}