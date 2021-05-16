package com.adintech.farmersguide.views.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.Disease;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ItemCropDiseaseBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CropDiseaseAdapter extends RecyclerView.Adapter<CropDiseaseAdapter.MyViewHolder> {

    // variables
    private Context mContext;
    private ArrayList<Disease> mCropDiseaseList;

    public CropDiseaseAdapter(Context mContext, ArrayList<Disease> mCropDiseaseList) {
        this.mContext = mContext;
        this.mCropDiseaseList = mCropDiseaseList;
    }

    @NonNull
    @Override
    public CropDiseaseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCropDiseaseBinding inflate = DataBindingUtil.inflate(
                LayoutInflater.from(mContext),
                R.layout.item_crop_disease,
                parent,
                false
        );
        CropDiseaseAdapter.MyViewHolder myViewHolder = new CropDiseaseAdapter.MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CropDiseaseAdapter.MyViewHolder holder, int position) {

        Disease disease = mCropDiseaseList.get(position);

        holder.itemCropDiseaseBinding.cropTitle.setText(disease.getTitle());
        holder.itemCropDiseaseBinding.cropDescription.setText(disease.getDescription());

        Glide.with(mContext)
                .load(disease.getImage())
                .into(holder.itemCropDiseaseBinding.cropImage);
        holder.itemCropDiseaseBinding.cardCropDisease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.setContentView(R.layout.alert_dialog_crop_disease);
                dialog.show();
                dialog.setCancelable(true);

                ImageView btnCancel = dialog.findViewById(R.id.btn_cancel);
                ImageView image = dialog.findViewById(R.id.img);
                TextView crop_disease_title = dialog.findViewById(R.id.crop_disease_title);
                TextView crop_disease_description = dialog.findViewById(R.id.crop_disease_description);
                TextView control_description = dialog.findViewById(R.id.control_description);

                //set data
                crop_disease_title.setText(disease.getTitle());
                crop_disease_description.setText(disease.getDescription());
                control_description.setText(disease.getControl());

                Glide.with(mContext)
                        .load(disease.getImage())
                        .into(image);

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                dialog.show();

            }
        });
    }

    @Override
    public int getItemCount() {
        return mCropDiseaseList != null ? mCropDiseaseList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemCropDiseaseBinding itemCropDiseaseBinding;
        public MyViewHolder(@NonNull ItemCropDiseaseBinding itemView) {
            super(itemView.getRoot());
            itemCropDiseaseBinding = itemView;
        }
    }
}
