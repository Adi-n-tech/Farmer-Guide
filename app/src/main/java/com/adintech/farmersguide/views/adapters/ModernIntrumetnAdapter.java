package com.adintech.farmersguide.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.ModernInstrument;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ItemModernIntrumentsBinding;

import com.bumptech.glide.Glide;
import com.google.gson.internal.$Gson$Preconditions;

import java.util.ArrayList;

public class ModernIntrumetnAdapter extends RecyclerView.Adapter<ModernIntrumetnAdapter.IntrumetnHolder> {

    //variables
    private Context context;
    private ArrayList<ModernInstrument> mModernInstruments;

    public ModernIntrumetnAdapter(Context context, ArrayList<ModernInstrument> mModernInstruments) {
        this.context = context;
        this.mModernInstruments = mModernInstruments;
    }

    @NonNull
    @Override
    public IntrumetnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemModernIntrumentsBinding inflate = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_modern_intruments,
                parent,
                false
        );
        ModernIntrumetnAdapter.IntrumetnHolder intrumetnHolder = new ModernIntrumetnAdapter.IntrumetnHolder(inflate);
        return intrumetnHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IntrumetnHolder holder, int position) {
        ModernInstrument modernInstrument = mModernInstruments.get(position);
        holder.itemModernIntrumentsBinding.discription.setText(modernInstrument.getDescription());
        holder.itemModernIntrumentsBinding.discriptionExpand.setText(modernInstrument.getDescription());
        holder.itemModernIntrumentsBinding.title.setText(modernInstrument.getName());
        holder.itemModernIntrumentsBinding.showmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemModernIntrumentsBinding.showmore.setVisibility(View.GONE);
                holder.itemModernIntrumentsBinding.showless.setVisibility(View.VISIBLE);
                holder.itemModernIntrumentsBinding.discription.setVisibility(View.GONE);
                holder.itemModernIntrumentsBinding.discriptionExpand.setVisibility(View.VISIBLE);
            }
        });
        holder.itemModernIntrumentsBinding.showless.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.itemModernIntrumentsBinding.showmore.setVisibility(View.VISIBLE);
                holder.itemModernIntrumentsBinding.showless.setVisibility(View.GONE);
                holder.itemModernIntrumentsBinding.discriptionExpand.setVisibility(View.GONE);
                holder.itemModernIntrumentsBinding.discription.setVisibility(View.VISIBLE);

            }
        });

        Glide.with(context)
                .load(modernInstrument.getImage())
                .into(holder.itemModernIntrumentsBinding.image);
    }

    @Override
    public int getItemCount() {
        {
            return mModernInstruments != null ? mModernInstruments.size() : 0;
        }

    }


    public void filterdlist( ArrayList<ModernInstrument> mModernInstruments) {
        this.mModernInstruments = mModernInstruments;
        notifyDataSetChanged();
    }

    public class IntrumetnHolder extends RecyclerView.ViewHolder {
        private ItemModernIntrumentsBinding itemModernIntrumentsBinding;
        public IntrumetnHolder(@NonNull ItemModernIntrumentsBinding itemView) {
            super(itemView.getRoot());
            itemModernIntrumentsBinding = itemView;
        }
    }
}