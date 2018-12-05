package com.eastwest.message.cardread;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.eastwest.message.cardread.database.Cheque;

import java.util.List;

/**
 * Created by SreeL1 on 10/26/2018.
 *
 */

public class ChequeRecyclerViewAdapter extends RecyclerView.Adapter<ChequeRecyclerViewAdapter.ViewHolder> {

    private List<Cheque> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    ChequeRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.row_layout, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(mData != null && mData.size() > 0) {
            Cheque data = mData.get(position);
            holder.idTV.setText(data.getUid());
            holder.amountTV.setText(data.getAmount());
            holder.dateTV.setText(data.getDate());
        }else{
            holder.amountTV.setText("No Data");
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        if(mData == null)
            return 0;
        return mData.size();
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView idTV;
        TextView amountTV;
        TextView dateTV;

        ViewHolder(View itemView) {
            super(itemView);
            idTV = itemView.findViewById(R.id.cheque_id);
            amountTV = itemView.findViewById(R.id.cheque_amount);
            dateTV = itemView.findViewById(R.id.cheque_date);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    void setCheques(List<Cheque> data){
        mData = data;
        notifyDataSetChanged();
    }

    // convenience method for getting data at click position
    Cheque getItem(int id) {
        if(mData == null)
            return null;
        return mData.get(id);
    }

    // notify the item removed by position
    public void removeItem(int position) {
        if( mData != null) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void restoreItem(Cheque data, int position) {
        if(mData != null) {
            mData.add(position, data);
            // notify item added by position
            notifyItemInserted(position);
        }
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
