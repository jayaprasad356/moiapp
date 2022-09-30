package com.greymatter.moi.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greymatter.moi.R;
import com.greymatter.moi.model.MoiReceiveddata;

import java.util.ArrayList;


public class MoiReceiveddetailsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<MoiReceiveddata> moiReceiveddata;
    final int layout;
    final int totalsize;


    public MoiReceiveddetailsAdapter(Activity activity, ArrayList<MoiReceiveddata> moiReceiveddata,int layout, int totalsize) {
        this.activity = activity;
        this.moiReceiveddata = moiReceiveddata;
        this.layout = layout;
        this.totalsize = totalsize;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(layout, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final MoiReceiveddata moiReceiveddata1 = moiReceiveddata.get(position);

        holder.tvsno.setText(position + 1 + "");
        holder.tvname.setText(moiReceiveddata1.getName());
        holder.tvmobile.setText(moiReceiveddata1.getMobile());
        holder.tvammount.setText(moiReceiveddata1.getAmount());
        holder.tvplace.setText(moiReceiveddata1.getLocation());


//        holder.tvFunPlace.setText("இடம் - "+functions1.getPlace());
//        holder.tvFunDate.setText("தேதி - "+functions1.getDate());


    }

    @Override
    public int getItemCount() {
        return moiReceiveddata.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {


        final TextView tvsno,tvname,tvmobile,tvplace,tvammount;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            tvsno = itemView.findViewById(R.id.tvsno);
            tvname = itemView.findViewById(R.id.tvname);
            tvmobile = itemView.findViewById(R.id.tvmobile);
            tvammount = itemView.findViewById(R.id.tvammount);
            tvplace = itemView.findViewById(R.id.tvplace);



        }
    }
}

