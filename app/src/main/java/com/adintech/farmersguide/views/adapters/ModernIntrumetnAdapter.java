package com.adintech.farmersguide.views.adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.ModernInstrument;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ItemModernIntrumentsBinding;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
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
        holder.itemModernIntrumentsBinding.shimmerViewContainer.startShimmerAnimation();
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
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        // failure
                        holder.itemModernIntrumentsBinding.shimmerViewContainer.stopShimmerAnimation();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        // success
                        holder.itemModernIntrumentsBinding.shimmerViewContainer.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(holder.itemModernIntrumentsBinding.modernImage);
    }

    @Override
    public int getItemCount() {
        {
            return mModernInstruments != null ? mModernInstruments.size() : 0;
        }
    }

    public void filterdlist(ArrayList<ModernInstrument> mModernInstruments) {
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