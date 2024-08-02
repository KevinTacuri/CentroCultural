package com.example.centrocultural;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.centrocultural.fragments.PaintingDetailFragment;

import java.util.List;

public class PaintingAdapter extends RecyclerView.Adapter<PaintingAdapter.PaintingViewHolder> {

    private List<Painting> paintings;
    private Context context;

    public PaintingAdapter(List<Painting> paintings, Context context) {
        this.paintings = paintings;
        this.context = context;
    }

    @NonNull
    @Override
    public PaintingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_painting, parent, false);
        return new PaintingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PaintingViewHolder holder, int position) {
        Painting painting = paintings.get(position);
        holder.nameTextView.setText(painting.getName());
        holder.imageView.setImageResource(painting.getImageResId());

        holder.itemView.setOnClickListener(v -> {
            if (context instanceof FragmentActivity) {
                FragmentActivity activity = (FragmentActivity) context;
                PaintingDetailFragment fragment = PaintingDetailFragment.newInstance(
                        painting.getName(),
                        painting.getImageResId(),
                        painting.getDescription(),
                        painting.getArtist(),
                        painting.getYear(),
                        painting.getAudioFileName()
                );
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainerView, fragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return paintings.size();
    }

    public static class PaintingViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        ImageView imageView;

        public PaintingViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}