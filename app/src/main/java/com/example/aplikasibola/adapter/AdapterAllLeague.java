package com.example.aplikasibola.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplikasibola.R;
import com.example.aplikasibola.activity.DetailLeagueActivity;
import com.example.aplikasibola.model.CountrysItem;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterAllLeague extends RecyclerView.Adapter<AdapterAllLeague.ViewHolder> {

    Context context;
    List<CountrysItem> items;

    public AdapterAllLeague(Context context, List<CountrysItem> items) {
        this.context = context;
        this.items = items;
    }

    public void setItems(List<CountrysItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_liga,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.tvNama.setText(items.get(position).getStrLeague());
        Picasso.get()
                .load(items.get(position).getStrBadge())
                .resize(75,75)
                .into(holder.ivLogo);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //actionclick
                Intent intent=new Intent(context, DetailLeagueActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("id",items.get(position).getIdLeague());
                intent.putExtras(mBundle);
                context.startActivity(intent);
            }

        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvNama;
        ImageView ivLogo;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNama=itemView.findViewById(R.id.tvNama);
            ivLogo=itemView.findViewById(R.id.ivLogo);
            this.itemView=itemView;
        }
    }

}
