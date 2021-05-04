package com.adintech.farmersguide.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.databinding.ItemYoutubeVideoBinding;
import com.adintech.farmersguide.model.YoutubeVideo;
import com.adintech.farmersguide.views.activities.YoutubePlayerActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.MyViewHolder> {

    //variables
    private Context context;
    private ArrayList<YoutubeVideo> youtubeVideoList;

    public YoutubeVideoAdapter(Context context, ArrayList<YoutubeVideo> youtubeVideoList) {
        this.context = context;
        this.youtubeVideoList = youtubeVideoList;
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
        YoutubeVideo youtubeVideo = youtubeVideoList.get(position);

        holder.itemYoutubeVideoBinding.videoTitle.setText(youtubeVideo.getVideoTitle());
        holder.itemYoutubeVideoBinding.videoDescription.setText(youtubeVideo.getVideoDescription());

        Picasso.get()
                .load(youtubeVideo.getVideoThumbnail())
                .placeholder(R.drawable.no_image_icon)
                .error(R.drawable.no_image_icon)
                .into(holder.itemYoutubeVideoBinding.videoThumbnail);

        holder.itemYoutubeVideoBinding.videoItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent video_item = new Intent(context, YoutubePlayerActivity.class);
                video_item.putExtra("YoutubeVideoList", youtubeVideo);
                context.startActivity(video_item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return youtubeVideoList != null ? youtubeVideoList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ItemYoutubeVideoBinding itemYoutubeVideoBinding;

        public MyViewHolder(@NonNull ItemYoutubeVideoBinding itemView) {
            super(itemView.getRoot());
            itemYoutubeVideoBinding = itemView;
        }
    }
}
