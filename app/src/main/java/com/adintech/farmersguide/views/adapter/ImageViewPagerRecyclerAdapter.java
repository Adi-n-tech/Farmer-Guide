package com.adintech.farmersguide.views.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ItemImageSliderBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class ImageViewPagerRecyclerAdapter extends RecyclerView.Adapter<ImageViewPagerRecyclerAdapter.ImageRecyclerViewHolder> {

    //variables
    private Context context;
    private ArrayList<String> photoList;

    public ImageViewPagerRecyclerAdapter(Context context, ArrayList<String> mFavPlacesImageList) {
        this.context = context;
        this.photoList = mFavPlacesImageList;
    }

    @NonNull
    @Override
    public ImageRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        ItemImageSliderBinding inflate = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_image_slider,
                viewGroup,
                false
        );
        ImageRecyclerViewHolder imageRecyclerViewHolder = new ImageRecyclerViewHolder(inflate);

        return imageRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageRecyclerViewHolder holder, int position) {
        String photoUrl = photoList.get(position);

        if (photoUrl != null && !photoUrl.isEmpty()) {
            loadImageFromPicasso(
                    photoUrl.trim(),
                    holder.itemImageSliderBinding.image,
                    holder.itemImageSliderBinding.imageProgress);
        }
    }

    @Override
    public int getItemCount() {
        return photoList != null ? photoList.size() : 0;
    }

    private void loadImageFromPicasso(String IMAGE_URL, final ImageView imageView, final ProgressBar progressBar) {
        //Configure target for
        com.squareup.picasso.Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                progressBar.setVisibility(View.GONE);
                imageView.setImageBitmap(bitmap);
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                progressBar.setVisibility(View.VISIBLE);
            }
        };

        imageView.setTag(target);

        if (!IMAGE_URL.isEmpty()) {
            Picasso.get()
                    .load(IMAGE_URL)
                    .error(R.drawable.no_image_icon)
                    .placeholder(R.drawable.no_image_icon)
                    .into(target);
        }
    }

    class ImageRecyclerViewHolder extends RecyclerView.ViewHolder {

        ItemImageSliderBinding itemImageSliderBinding;

        public ImageRecyclerViewHolder(@NonNull ItemImageSliderBinding itemView) {
            super(itemView.getRoot());
            itemImageSliderBinding = itemView;
        }
    }
}
