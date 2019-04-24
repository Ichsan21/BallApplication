package com.example.aplikasibola.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aplikasibola.R;
import com.example.aplikasibola.httpclient.ApiInterface;
import com.example.aplikasibola.model.EventsItem;
import com.example.aplikasibola.model.ResponseDetailLeague;
import com.example.aplikasibola.model.ResponseLookupTeam;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterAllEvent extends RecyclerView.Adapter<AdapterAllEvent.ViewHolder>{

    Context context;
    List<EventsItem> items;
    ApiInterface mApi;

    public AdapterAllEvent(Context context, List<EventsItem> items, ApiInterface mApi) {
        this.context = context;
        this.items = items;
        this.mApi = mApi;
    }

    public void setItems(List<EventsItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AdapterAllEvent.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_detail_league,parent,false);

        return new ViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDate.setText(items.get(position).getDateEvent());
        holder.tvScoreAway.setText(items.get(position).getIntAwayScore());
        holder.tvScoreHome.setText(items.get(position).getIntHomeScore());
        holder.tvTeam.setText(items.get(position).getStrEvent());

        callApiLookupItem(items.get(position).getIdAwayTeam(), holder.ivAway);
        callApiLookupItem(items.get(position).getIdHomeTeam(), holder.ivHome);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //action on click
            }
        });

        //Picasso.get()
        //        .load(items.get(position).getStrThumb())
        //        .fit()
        //        .into(holder.ivAway);
    }

    @Override
    public int getItemCount(){
        return items.size();
    }

    public void callApiLookupItem(String id, final ImageView ivteam) {

        Call<ResponseLookupTeam> api=mApi.getLookupTeam(id);

        api.enqueue(new Callback<ResponseLookupTeam>() {
            @Override
            public void onResponse(Call<ResponseLookupTeam> call, Response<ResponseLookupTeam> response) {
                if (response.isSuccessful()){
                    Picasso.get()
                            .load(response.body().getTeams().get(0).getStrTeamBadge())
                            .into(ivteam);
                }
            }

            @Override
            public void onFailure(Call<ResponseLookupTeam> call, Throwable t) {

            }
        });

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTeam;
        TextView tvScoreAway;
        TextView tvScoreHome;
        TextView tvDate;
        ImageView ivHome;
        ImageView ivAway;
        View itemView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTeam=itemView.findViewById(R.id.tvTeam);
            tvScoreAway=itemView.findViewById(R.id.tvScoreAway);
            tvScoreHome=itemView.findViewById(R.id.tvScoreHome);
            tvDate=itemView.findViewById(R.id.tvDate);
            ivHome=itemView.findViewById(R.id.ivHome);
            ivAway=itemView.findViewById(R.id.ivAway);
            this.itemView=itemView;
        }
    }

}
