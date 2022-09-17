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

import com.GreyMatter.moi.R;
import com.GreyMatter.moi.helper.Session;
import com.GreyMatter.moi.model.Attendingfunction;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class AttendingfunctionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Attendingfunction> attendingfunctions;
    Session session;

    public AttendingfunctionAdapter(Activity activity, ArrayList<Attendingfunction> attendingfunctions) {
        this.activity = activity;
        this.attendingfunctions = attendingfunctions;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.attending_function_list, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Attendingfunction attendingfunction = attendingfunctions.get(position);

        Glide.with(activity).load(attendingfunction.getImgview()).placeholder(R.drawable.fun_pic).into(holder.imageView);
        holder.tvFunName.setText(attendingfunction.getFunname());
        holder.tvFunPlace.setText(attendingfunction.getFunplace());
        holder.tvFunDate.setText(attendingfunction.getFundate());



//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(activity, ProductlistActivity.class);
//                intent.putExtra(Constant.CATEGORY_ID,category.getId());
//                session.setData(Constant.CATEGORY,category.getName());
//                activity.startActivity(intent);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return attendingfunctions.size();
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

