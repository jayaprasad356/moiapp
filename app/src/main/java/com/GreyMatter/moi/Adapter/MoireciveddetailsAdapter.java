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

import com.GreyMatter.moi.MoirResivedDetailsActivity;
import com.GreyMatter.moi.R;
import com.GreyMatter.moi.helper.Session;
import com.GreyMatter.moi.model.Moireciveddetails;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MoireciveddetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Moireciveddetails> moireciveddetails;
    Session session;

    public MoireciveddetailsAdapter(Activity activity, ArrayList<Moireciveddetails> moireciveddetails) {
        this.activity = activity;
        this.moireciveddetails = moireciveddetails;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.moi_recived_details, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        session = new Session(activity);
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Moireciveddetails moireciveddetail = moireciveddetails.get(position);


        holder.tvsno.setText(moireciveddetail.getS_no());
        holder.tvname.setText(moireciveddetail.getName());
        holder.tvmobile.setText(moireciveddetail.getMobile_no());
        holder.tvplace.setText(moireciveddetail.getPlace());
        holder.tvammount.setText(moireciveddetail.getAmount());




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, MoirResivedDetailsActivity.class);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moireciveddetails.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {


        final TextView tvsno,tvname,tvmobile,tvplace,tvammount;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvsno = itemView.findViewById(R.id.tvsno);
            tvname = itemView.findViewById(R.id.tvname);
            tvmobile = itemView.findViewById(R.id.tvmobile);
            tvplace = itemView.findViewById(R.id.tvplace);
            tvammount = itemView.findViewById(R.id.tvammount);



        }
    }
}

