package com.example.centrocultural;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private List<Room> rooms;
    private Context context;

    public RoomAdapter(List<Room> rooms, Context context) {
        this.rooms = rooms;
        this.context = context;
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = rooms.get(position);
        holder.nameTextView.setText(room.getName());

        holder.itemView.setOnClickListener(v -> {
            // Toggle visibility of paintings RecyclerView
            if (holder.paintingsRecyclerView.getVisibility() == View.VISIBLE) {
                holder.paintingsRecyclerView.setVisibility(View.GONE);
            } else {
                holder.paintingsRecyclerView.setVisibility(View.VISIBLE);
            }
        });

        // Set up the paintings RecyclerView
        PaintingAdapter paintingAdapter = new PaintingAdapter(room.getPaintings(), context);
        holder.paintingsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.paintingsRecyclerView.setAdapter(paintingAdapter);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class RoomViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        RecyclerView paintingsRecyclerView;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            paintingsRecyclerView = itemView.findViewById(R.id.paintingsRecyclerView);
        }
    }
}

