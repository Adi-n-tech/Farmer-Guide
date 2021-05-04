package com.adintech.farmersguide.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.Crop;
import com.adintech.farmersguide.R;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.iteam_detail,parent,false);
        return new CropHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CropHolder holder, int position) {
        Crop crop = mCropArrayList.get(position);
        holder.cropsTitles.setText(crop.getTitle());
        holder.cropsDesriptions.setText(crop.getDescription());
        Glide.with(mContext)
                .load(crop.getImageUrl())
                .into(holder.cropsImage);
    }

    @Override
    public int getItemCount() {
        return mCropArrayList!=null?mCropArrayList.size():0;
    }

    public class CropHolder extends RecyclerView.ViewHolder {
        ImageView cropsImage;
        TextView cropsTitles,cropsDesriptions;
        public CropHolder(@NonNull View itemView) {
            super(itemView);
            cropsImage = itemView.findViewById(R.id.image);
            cropsDesriptions = itemView.findViewById(R.id.discription);
            cropsTitles = itemView.findViewById(R.id.title);
        }
    }
}
