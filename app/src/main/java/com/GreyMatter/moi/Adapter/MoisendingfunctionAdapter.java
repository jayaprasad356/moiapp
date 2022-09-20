package com.GreyMatter.moi.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GreyMatter.moi.MoisentamountActivity;
import com.GreyMatter.moi.R;
import com.GreyMatter.moi.helper.Session;
import com.GreyMatter.moi.model.Moisendingfunction;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MoisendingfunctionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Moisendingfunction> moisendingfunctions;
    Session session;

    public MoisendingfunctionAdapter(Activity activity, ArrayList<Moisendingfunction> moisendingfunctions) {
        this.activity = activity;
        this.moisendingfunctions = moisendingfunctions;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.moi_sending_function, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Moisendingfunction moiupcomingfunctions = this.moisendingfunctions.get(position);

        Glide.with(activity).load(moiupcomingfunctions.getImgview()).placeholder(R.drawable.fun_pic).into(holder.imageView);
        holder.tvFunName.setText(moiupcomingfunctions.getFunname());
        holder.tvFunPlace.setText(moiupcomingfunctions.getFunplace());
        holder.tvFunDate.setText(moiupcomingfunctions.getFundate());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, MoisentamountActivity.class);

                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moisendingfunctions.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final ImageView imageView;
        final TextView tvFunName,tvFunPlace,tvFunDate;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvFunName = itemView.findViewById(R.id.tvFunName);
            tvFunPlace = itemView.findViewById(R.id.tvFunPlace);
            tvFunDate = itemView.findViewById(R.id.tvFunDate);



        }
    }
}

