package com.greymatter.moi.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.greymatter.moi.MoiReceiveActivity;
import com.greymatter.moi.MoirReceivedDetailsActivity;
import com.greymatter.moi.R;
import com.greymatter.moi.helper.Constant;
import com.greymatter.moi.model.Functions;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class FunctionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    final Activity activity;
    final ArrayList<Functions> functions;
    String MOiIntent;

    public FunctionAdapter(Activity activity, ArrayList<Functions> functions, String MOiIntent) {
        this.activity = activity;
        this.functions = functions;
        this.MOiIntent = MOiIntent;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.function_layout, parent, false);
        return new ExploreItemHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holderParent, int position) {
        final ExploreItemHolder holder = (ExploreItemHolder) holderParent;
        final Functions functions1 = functions.get(position);

        Glide.with(activity).load(functions1.getImage()).placeholder(R.drawable.fun_pic).into(holder.imgFunction);
        holder.tvFunName.setText(functions1.getFunction_name());
        holder.tvFunPlace.setText("இடம் - "+functions1.getPlace());
        holder.tvFunDate.setText("தேதி - "+functions1.getDate());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MOiIntent.equals("addmoi")){
                    Intent intent = new Intent(activity, MoiReceiveActivity.class);
                    intent.putExtra(Constant.FUNCTIONID,functions1.getId());
                    intent.putExtra(Constant.FUNCTIONAME,functions1.getFunction_name());
                    activity.startActivity(intent);

                }else {
                    Intent intent = new Intent(activity, MoirReceivedDetailsActivity.class);
                    intent.putExtra(Constant.FUNCTIONID,functions1.getId());
                    intent.putExtra(Constant.FUNCTIONAME,functions1.getFunction_name());
                    activity.startActivity(intent);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return functions.size();
    }

    static class ExploreItemHolder extends RecyclerView.ViewHolder {

        final ImageView imgFunction;
        final TextView tvFunName,tvFunPlace,tvFunDate;
        public ExploreItemHolder(@NonNull View itemView) {
            super(itemView);
            imgFunction = itemView.findViewById(R.id.imgFunction);
            tvFunName = itemView.findViewById(R.id.tvFunName);
            tvFunPlace = itemView.findViewById(R.id.tvFunPlace);
            tvFunDate = itemView.findViewById(R.id.tvFunDate);



        }
    }
}

