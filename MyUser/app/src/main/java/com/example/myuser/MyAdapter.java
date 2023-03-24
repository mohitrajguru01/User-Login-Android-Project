package com.example.myuser;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Model[] data;
    String ImageUrl;

    public MyAdapter(Model[] data, String ImageUrl) {
        this.data = data;
        this.ImageUrl = ImageUrl;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.name.setText(data[position].getName());
        holder.email.setText(data[position].getEmail());
        Glide.with(holder.name.getContext()).load(ImageUrl + data[position].getImage()).into(holder.user_image);
        holder.view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int index = holder.getAdapterPosition();
                Intent intent = new Intent(holder.name.getContext(), UserProfile.class);
                intent.putExtra("name", data[index].getName());
                intent.putExtra("username", data[index].getUsername());
                intent.putExtra("email", data[index].getEmail());
                intent.putExtra("img_url", ImageUrl + data[index].getImage());
                holder.name.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name = itemView.findViewById(R.id.user_name);
        TextView email = itemView.findViewById(R.id.user_email);
        ImageView user_image = itemView.findViewById(R.id.user_image);
        Button view_profile = itemView.findViewById(R.id.view_profile);
        public ViewHolder(@NonNull View view) {
            super(view);
        }
    }
}
