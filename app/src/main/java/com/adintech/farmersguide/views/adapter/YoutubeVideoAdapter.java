package com.adintech.farmersguide.views.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.Util.preference.AppPreferencesManager;
import com.adintech.farmersguide.views.activities.YoutubePlayerActivity;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class YoutubeVideoAdapter extends RecyclerView.Adapter<YoutubeVideoAdapter.MyViewHolder> {
    Context context;
    ArrayList video_title;
    ArrayList video_description;
    ArrayList thumbnail;
    ArrayList youtube_link;
    private String youtubeLink, mTitle, mDescription;

    public YoutubeVideoAdapter(Context context, ArrayList video_title, ArrayList video_description, ArrayList<String> thumbnail, ArrayList youtube_link) {
        this.context = context;
        this.video_title = video_title;
        this.video_description = video_description;
        this.thumbnail = thumbnail;
        this.youtube_link = youtube_link;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_video, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // youtubeLink = (String) youtube_link.get(position);

        holder.mVideo_title.setText((CharSequence) video_title.get(position));
        holder.mVideo_description.setText((String) video_description.get(position));

        Picasso.get()
                .load(thumbnail.get(position).toString())
                .fit()
                .placeholder(R.drawable.no_image_icon)
                .error(R.drawable.no_image_icon)
                .into(holder.mVideo_thumbnail);

/*

        mTitle = (String) video_title.get(position);
        mDescription = (String) video_description.get(position);
*/

        holder.video_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent video_item = new Intent(context, YoutubePlayerActivity.class);
                video_item.putExtra(AppConstants.YOUTUBE_KEYS.TITLE, video_title.get(position).toString());
                video_item.putExtra(AppConstants.YOUTUBE_KEYS.DESCRIPTION, video_description.get(position).toString());
                video_item.putExtra(AppConstants.YOUTUBE_KEYS.LINK, youtube_link.get(position).toString());
                context.startActivity(video_item);
            }
        });

    }

    @Override
    public int getItemCount() {
        return video_title.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mVideo_title;
        private TextView mVideo_description;
        private ImageView mVideo_thumbnail;
        private CardView video_item;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mVideo_thumbnail = itemView.findViewById(R.id.video_thumbnail);
            mVideo_title = itemView.findViewById(R.id.video_title);
            mVideo_description = itemView.findViewById(R.id.video_description);
            video_item = itemView.findViewById(R.id.video_item);

        }
    }
}
