package com.GreyMatter.moi.Adapter;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GreyMatter.moi.R;
import com.GreyMatter.moi.helper.Session;

import com.GreyMatter.moi.model.Moisend;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MoisendAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Moisend> moisends;
    Session session;

    public MoisendAdapter(Activity activity, ArrayList<Moisend> moisends) {
        this.activity = activity;
        this.moisends = moisends;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.moi_send_list, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Moisend moisend = moisends.get(position);

        Glide.with(activity).load(moisend.getImgview()).placeholder(R.drawable.fun_pic).into(holder.imageView);
        holder.tvFunName.setText(moisend.getFunname());
        holder.tvFunPlace.setText(moisend.getFunplace());
        holder.tvFunDate.setText(moisend.getFundate());



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
        return moisends.size();
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

