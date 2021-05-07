package com.adintech.farmersguide.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.Crop;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.databinding.ItemCropsBinding;
import com.adintech.farmersguide.views.activities.CropDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class CropsAdapters extends RecyclerView.Adapter<CropsAdapters.CropHolder> {

    // variables
    private ArrayList<Crop> mCropArrayList;
    private Context mContext;

    public CropsAdapters(ArrayList<Crop> cropArrayList, Context mContext) {
        this.mCropArrayList = cropArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public CropHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCropsBinding inflate = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.item_crops,
                parent,
                false
        );
        CropsAdapters.CropHolder myViewHolder = new CropsAdapters.CropHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CropHolder holder, int position) {
        Crop crop = mCropArrayList.get(position);

        holder.itemCropsBinding.title.setText(crop.getTitle());
        holder.itemCropsBinding.description.setText(crop.getDescription());
        Glide.with(mContext)
                .load(crop.getImageUrl())
                .into(holder.itemCropsBinding.image);

        holder.itemCropsBinding.cardItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, CropDetailActivity.class);
                intent.putExtra(AppConstants.INTENT_KEYS.CROP, crop);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCropArrayList != null ? mCropArrayList.size() : 0;
    }

    public void filterlist(ArrayList<Crop> mCropArrayList) {
        this.mCropArrayList = mCropArrayList;
        notifyDataSetChanged();
    }


    public class CropHolder extends RecyclerView.ViewHolder {
        private ItemCropsBinding itemCropsBinding;

        public CropHolder(@NonNull ItemCropsBinding itemView) {
            super(itemView.getRoot());
            itemCropsBinding = itemView;
        }
    }
}
