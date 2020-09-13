package com.example.ex1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class WinnerAdapter extends RecyclerView.Adapter<WinnerAdapter.WinnerViewHolder> {
    private  ArrayList<Winner> winnerArrayList;
    private  OnWinnerClickListener wListener;

    public interface OnWinnerClickListener {
        void onWinnerClick(int position);
    }

    public void setOnWinnerClickListener(OnWinnerClickListener listener){
        wListener = listener;
    }

    public static class WinnerViewHolder extends RecyclerView.ViewHolder{
        public TextView winnerItem_LBL_line1;
        public TextView winnerItem_LBL_line2;
        public WinnerViewHolder(View itemView, final OnWinnerClickListener listener) {
            super(itemView);
            winnerItem_LBL_line1 = itemView.findViewById(R.id.winnerItem_LBL_line1);
            winnerItem_LBL_line2 = itemView.findViewById(R.id.winnerItem_LBL_line2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onWinnerClick(position);
                        }
                    }
                }
            });
        }
    }

    public WinnerAdapter(ArrayList<Winner> winnerArrayList){
        this.winnerArrayList = winnerArrayList;
    }

    @NonNull
    @Override
    public WinnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.winner_item, parent,false);
        WinnerViewHolder wvh = new WinnerViewHolder(v, wListener);
        return wvh;
    }

    @Override
    public void onBindViewHolder(WinnerViewHolder holder, int position) {
        Winner currentWinner = winnerArrayList.get(position);

        holder.winnerItem_LBL_line1.setText(currentWinner.getName());
        holder.winnerItem_LBL_line2.setText("" + currentWinner.getCounterMoves());
    }

    @Override
    public int getItemCount() {
        return winnerArrayList.size();
    }
}
