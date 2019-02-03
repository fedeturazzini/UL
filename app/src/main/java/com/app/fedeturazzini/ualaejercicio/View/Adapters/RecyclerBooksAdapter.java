package com.app.fedeturazzini.ualaejercicio.View.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerBooksAdapter extends RecyclerView.Adapter<RecyclerBooksAdapter.MyViewHolder> {
    @NonNull
    @Override
    public RecyclerBooksAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerBooksAdapter.MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
