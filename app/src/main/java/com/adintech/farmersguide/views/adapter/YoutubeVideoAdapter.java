package com.adintech.farmersguide.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.databinding.ItemYoutubeVideoBinding;
import com.adintech.farmersguide.Models.YoutubeVideo;
import com.adintech.farmersguide.views.activities.YoutubePlayerActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.MyViewHolder> {

    //variables
    private Context context;
    private ArrayList<YoutubeVideo> mYoutubeVideoList;

    public YoutubeVideoAdapter(Context context, ArrayList<YoutubeVideo> mYoutubeVideoList) {
        this.context = context;
        this.mYoutubeVideoList = mYoutubeVideoList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemYoutubeVideoBinding inflate = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.item_youtube_video,
                parent,
                false
        );
        YoutubeVideoAdapter.MyViewHolder myViewHolder = new YoutubeVideoAdapter.MyViewHolder(inflate);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        YoutubeVideo video = mYoutubeVideoList.get(position);
        holder.itemYoutubeVideoBinding.shimmerViewContainer.startShimmerAnimation();
        holder.itemYoutubeVideoBinding.videoTitle.setText(video.getVideoTitle());
        holder.itemYoutubeVideoBinding.videoDescription.setText(video.getVideoDescription());

        Glide.with(context)
                .load(video.getVideoThumbnail())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.itemYoutubeVideoBinding.shimmerViewContainer.stopShimmerAnimation();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.itemYoutubeVideoBinding.shimmerViewContainer.stopShimmerAnimation();
                        return false;
                    }
                })
                .into(holder.itemYoutubeVideoBinding.videoThumbnail);

        holder.itemYoutubeVideoBinding.videoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent video_item = new Intent(context, YoutubePlayerActivity.class);
                video_item.putExtra(AppConstants.INTENT_KEYS.YOUTUBE_VIDEO, video);
                context.startActivity(video_item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mYoutubeVideoList != null ? mYoutubeVideoList.size() : 0;
    }

    public void filteredlist(ArrayList<YoutubeVideo> mYoutubeVideoList) {
        this.mYoutubeVideoList = mYoutubeVideoList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemYoutubeVideoBinding itemYoutubeVideoBinding;

        public MyViewHolder(@NonNull ItemYoutubeVideoBinding itemView) {
            super(itemView.getRoot());
            itemYoutubeVideoBinding = itemView;
        }
    }
}
