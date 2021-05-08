package com.adintech.farmersguide.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.Models.Farmingmethod;
import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ActivityModernfarmingMethodBinding;
import com.adintech.farmersguide.databinding.ItemModernIntrumentsBinding;
import com.adintech.farmersguide.databinding.ItemModernMethodBinding;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FarmingMethodAdapter extends RecyclerView.Adapter<FarmingMethodAdapter.FarmingHolder>{

    Context context;
    ArrayList<Farmingmethod> arrayList;

    public FarmingMethodAdapter(Context context, ArrayList<Farmingmethod> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public FarmingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemModernMethodBinding inflate = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_modern_method,
                parent,
                false
        );
        FarmingMethodAdapter.FarmingHolder farmingHolder = new FarmingMethodAdapter.FarmingHolder(inflate);
        return farmingHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FarmingHolder holder, int position) {

        Farmingmethod farmingmethod = arrayList.get(position);
        holder.mItemModernMethodBinding.title.setText(farmingmethod.getTitle());
        holder.mItemModernMethodBinding.description.setText(farmingmethod.getDescription());
        Glide.with(context)
                .load(farmingmethod.getImg())
                .into(holder.mItemModernMethodBinding.image);

    }

    @Override
    public int getItemCount() {
        return  arrayList != null ? arrayList.size() : 0;
    }

    public void filterdlist(ArrayList<Farmingmethod> mArrayList) {
        this.arrayList = mArrayList;
        notifyDataSetChanged();
    }


    public class FarmingHolder extends RecyclerView.ViewHolder {
        private ItemModernMethodBinding mItemModernMethodBinding;

        public FarmingHolder(@NonNull ItemModernMethodBinding itemView) {
            super(itemView.getRoot());
            mItemModernMethodBinding = itemView;
        }
    }
}
