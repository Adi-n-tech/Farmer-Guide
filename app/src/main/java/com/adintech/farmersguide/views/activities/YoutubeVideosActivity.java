package com.adintech.farmersguide.views.activities;

import android.os.Bundle;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.Utility;
import com.adintech.farmersguide.databinding.ActivityYoutubeVideoListBinding;
import com.adintech.farmersguide.Models.YoutubeResponse;
import com.adintech.farmersguide.Models.YoutubeVideo;
import com.adintech.farmersguide.views.adapter.YoutubeVideoAdapter;
import com.google.gson.Gson;

import java.util.ArrayList;

public class YoutubeVideosActivity extends AppCompatActivity {

    //variables
    private ActivityYoutubeVideoListBinding mActivityYoutubeVideoListBinding;
    private RecyclerView mYoutubeVideoListRecycleView;
    private YoutubeVideoAdapter youtubeVideoAdapter;
    private ArrayList<String> video_title = new ArrayList<>();
    private ArrayList<String> youtube_link = new ArrayList<>();
    private ArrayList<String> video_description = new ArrayList<>();
    private ArrayList<String> video_thumbnail = new ArrayList<>();

    private ArrayList<YoutubeVideo> youtubeVideoList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityYoutubeVideoListBinding = DataBindingUtil.setContentView(this, R.layout.activity_youtube_video_list);
        getSupportActionBar().setTitle("UseFull Video");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initialize();
        search();
    }

    private void search() {
        mActivityYoutubeVideoListBinding.searchview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void filter(String newText) {
        ArrayList<YoutubeVideo> filterlist = new ArrayList<>();
        for (YoutubeVideo youtubeVideo : youtubeVideoList){
            if (youtubeVideo.getVideoTitle().contains(newText.toLowerCase())){
                filterlist.add(youtubeVideo);
            }
            youtubeVideoAdapter.filteredlist(filterlist);
        }
    }

    private void initialize() {
        mYoutubeVideoListRecycleView = mActivityYoutubeVideoListBinding.recycle;

        String jsonString = Utility.loadJSONFromAsset(this, "doGetYoutubeVideos");
        YoutubeResponse response = new Gson().fromJson(jsonString, YoutubeResponse.class);

        youtubeVideoList = response.getYoutubeVideoList();
        doConfigureSearchBarView();
        setYoutubeDataAdapter();
    }
    private void doConfigureSearchBarView() {
        mActivityYoutubeVideoListBinding.searchview.setIconifiedByDefault(false);
        mActivityYoutubeVideoListBinding.searchview.setFocusable(false);
        mActivityYoutubeVideoListBinding.searchview.setQueryHint("Search here ..");
    }

    private void setYoutubeDataAdapter() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mYoutubeVideoListRecycleView.setLayoutManager(linearLayoutManager);
         youtubeVideoAdapter = new YoutubeVideoAdapter(this, youtubeVideoList);
        mYoutubeVideoListRecycleView.setAdapter(youtubeVideoAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}