package com.example.minesweeper;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MineGridRecyclerAdapter extends RecyclerView.Adapter<MineGridRecyclerAdapter.MineTileViewHolder> {
    private List<Cell> cells;
    private OnCellClickListener listener;

    public MineGridRecyclerAdapter(List<Cell> cells, OnCellClickListener listener) {
        this.cells = cells;
        this.listener = listener;
    }
    @NonNull
    @Override
    public MineTileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, attachToRoot, false);
        return new MineTileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MineTileViewHolder holder, int position) {
        holder.bind(cells.get(position));
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() { return cells.size(); }

    public void setCells(List<Cell> cells){
        this.cells = cells;
        notifyDataSetChanged();
    }

    class MineTileViewHolder extends RecyclerView.ViewHolder {
            TextView valueTextView;

            public MineTileViewHolder(@NonNull view itemView){
                super(itemView);

                valueTextView = itemView.findViewById(R.id.item_cell_value);
            }

        }

        public void bind(final Cell cell) {
            itemView.setBackgroundColor(Color.GREEN);

            itemView.setOnClickListener((view) {
                @Override
                public void onClick(View view)
                listener.onCellClick(cell);
                }
            });
        }
    }
}
