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

import com.GreyMatter.moi.AmountrecivedActivity;
import com.GreyMatter.moi.MoirResivedDetailsActivity;
import com.GreyMatter.moi.R;
import com.GreyMatter.moi.helper.Session;
import com.GreyMatter.moi.model.Moirecived;
import com.GreyMatter.moi.model.Moirecivingfunction;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MoirecivingfunctionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Moirecivingfunction> moirecivingfunctions;
    Session session;

    public MoirecivingfunctionAdapter(Activity activity, ArrayList<Moirecivingfunction> moirecivingfunctions) {
        this.activity = activity;
        this.moirecivingfunctions = moirecivingfunctions;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.moi_recivingfunction_list, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Moirecivingfunction moirecivingfunction = moirecivingfunctions.get(position);

        Glide.with(activity).load(moirecivingfunction.getImgview()).placeholder(R.drawable.fun_pic).into(holder.imageView);
        holder.tvFunName.setText(moirecivingfunction.getFunname());
        holder.tvFunPlace.setText(moirecivingfunction.getFunplace());
        holder.tvFunDate.setText(moirecivingfunction.getFundate());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, AmountrecivedActivity.class);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moirecivingfunctions.size();
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

