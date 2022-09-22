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
import com.GreyMatter.moi.model.MoiSentFunctions;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MoiSentFunctionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<MoiSentFunctions> moiSentFunctions;

    public MoiSentFunctionAdapter(Activity activity, ArrayList<MoiSentFunctions> moiSentFunctions) {
        this.activity = activity;
        this.moiSentFunctions = moiSentFunctions;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.moi_sent_item_layout, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final MoiSentFunctions moiSentFunctions1 = moiSentFunctions.get(position);

        Glide.with(activity).load(moiSentFunctions1.getImage()).placeholder(R.drawable.fun_pic).into(holder.imgFunction);
        holder.tvFunName.setText(moiSentFunctions1.getFunction_name());
        holder.tvFunPlace.setText("இடம் - "+moiSentFunctions1.getPlace());
        holder.tvFunDate.setText("தேதி - "+moiSentFunctions1.getDate());
        holder.tvAmount.setText("தொகை - "+moiSentFunctions1.getAmount());

    }

    @Override
    public int getItemCount() {
        return moiSentFunctions.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final ImageView imgFunction;
        final TextView tvFunName,tvFunPlace,tvFunDate,tvAmount;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            imgFunction = itemView.findViewById(R.id.imgFunction);
            tvFunName = itemView.findViewById(R.id.tvFunName);
            tvFunPlace = itemView.findViewById(R.id.tvFunPlace);
            tvFunDate = itemView.findViewById(R.id.tvFunDate);
            tvAmount = itemView.findViewById(R.id.tvAmount);



        }
    }
}

