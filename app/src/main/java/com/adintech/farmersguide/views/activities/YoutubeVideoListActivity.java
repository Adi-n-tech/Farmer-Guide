package com.adintech.farmersguide.views.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.databinding.ActivityYoutubeVideoListBinding;
import com.adintech.farmersguide.views.adapter.YoutubeVideoAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class YoutubeVideoListActivity extends AppCompatActivity {
    ActivityYoutubeVideoListBinding mActivityYoutubeVideoListBinding;
    RecyclerView mYoutubeVideoListRecycleView;


    private ArrayList<String> video_title = new ArrayList<>();
    private ArrayList<String> youtube_link = new ArrayList<>();
    private ArrayList<String> video_description = new ArrayList<>();
    private ArrayList<String> video_thumbnail = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityYoutubeVideoListBinding = DataBindingUtil.setContentView(this, R.layout.activity_youtube_video_list);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialize();
    }

    private void initialize() {
        mYoutubeVideoListRecycleView = mActivityYoutubeVideoListBinding.recycle;

        try {
            JSONObject obj = new JSONObject(Utility.loadJSONFromAsset(this, "doGetYoutubeVideos.json"));
            JSONArray cQueList = obj.getJSONArray("YoutubeVideoList");
            for (int i = 0; i < cQueList.length(); i++) {
                JSONObject queAns = cQueList.getJSONObject(i);
                video_title.add(queAns.getString("videoTitle"));
                video_description.add(queAns.getString("videoDescription"));
                video_thumbnail.add(queAns.getString("videoThumbnail"));
                youtube_link.add(queAns.getString("videoLink"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        setYoutubeDataAdapter();
    }

    private void setYoutubeDataAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mYoutubeVideoListRecycleView.setLayoutManager(linearLayoutManager);
        YoutubeVideoAdapter youtubeVideoAdapter = new YoutubeVideoAdapter(this, video_title, video_description, video_thumbnail, youtube_link);
        mYoutubeVideoListRecycleView.setAdapter(youtubeVideoAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}