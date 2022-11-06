package com.greymatter.moi.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.greymatter.moi.R;
import com.greymatter.moi.model.MoiCompareFunctions;
import com.greymatter.moi.model.MoiSentFunctions;

import java.util.ArrayList;


public class MoiCompareAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<MoiCompareFunctions> moiCompareFunctions;

    public MoiCompareAdapter(Activity activity, ArrayList<MoiCompareFunctions> moiCompareFunctions) {
        this.activity = activity;
        this.moiCompareFunctions = moiCompareFunctions;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.moi_compare_layout, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final MoiCompareFunctions moiCompareFunctions1 = moiCompareFunctions.get(position);

        Glide.with(activity).load(moiCompareFunctions1.getImage()).placeholder(R.drawable.fun_pic).into(holder.imgFunction);
        holder.tvFunName.setText(moiCompareFunctions1.getFunction_name());
        holder.tvFunPlace.setText("இடம் - "+moiCompareFunctions1.getPlace());
        holder.tvFunDate.setText("தேதி - "+moiCompareFunctions1.getDate());
        holder.tvAmount.setText("தொகை - "+moiCompareFunctions1.getAmount());

    }

    @Override
    public int getItemCount() {
        return moiCompareFunctions.size();
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

