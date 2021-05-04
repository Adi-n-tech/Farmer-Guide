package com.adintech.farmersguide.views.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.databinding.ActivityYoutubeVideoListBinding;
import com.adintech.farmersguide.model.YoutubeResponse;
import com.adintech.farmersguide.model.YoutubeVideo;
import com.adintech.farmersguide.views.adapter.YoutubeVideoAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class YoutubeVideoListActivity extends AppCompatActivity {

    //variables
    private ActivityYoutubeVideoListBinding mActivityYoutubeVideoListBinding;
    private RecyclerView mYoutubeVideoListRecycleView;
    private ArrayList<String> video_title = new ArrayList<>();
    private ArrayList<String> youtube_link = new ArrayList<>();
    private ArrayList<String> video_description = new ArrayList<>();
    private ArrayList<String> video_thumbnail = new ArrayList<>();

    private ArrayList<YoutubeVideo> youtubeVideoList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityYoutubeVideoListBinding = DataBindingUtil.setContentView(this, R.layout.activity_youtube_video_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialize();
    }

    private void initialize() {
        mYoutubeVideoListRecycleView = mActivityYoutubeVideoListBinding.recycle;

        String jsonString = Utility.loadJSONFromAsset(this, "doGetYoutubeVideos");
        YoutubeResponse response = new Gson().fromJson(jsonString, YoutubeResponse.class);

        youtubeVideoList = response.getYoutubeVideoList();

        setYoutubeDataAdapter();
    }

    private void setYoutubeDataAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mYoutubeVideoListRecycleView.setLayoutManager(linearLayoutManager);
        YoutubeVideoAdapter youtubeVideoAdapter = new YoutubeVideoAdapter(this, youtubeVideoList);
        mYoutubeVideoListRecycleView.setAdapter(youtubeVideoAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}