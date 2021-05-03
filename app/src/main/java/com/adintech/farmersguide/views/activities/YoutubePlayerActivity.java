package com.adintech.farmersguide.views.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.adintech.farmersguide.R;
import com.adintech.farmersguide.Util.constant.AppConstants;
import com.adintech.farmersguide.databinding.ActivityYoutubePlayerBinding;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubePlayerActivity extends AppCompatActivity {

    private final static String expression = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)[^#\\&\\?\\n]*";
    ActivityYoutubePlayerBinding mActivityYoutubePlayerAcitivityBinding;
    String title, description, link;

    public static String getVideoId(String videoUrl) {
        if (videoUrl == null || videoUrl.trim().length() <= 0) {
            return null;
        }
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(videoUrl);
        try {
            if (matcher.find())
                return matcher.group();
        } catch (ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //back button enabled
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mActivityYoutubePlayerAcitivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_youtube_player);

        Intent intent = getIntent();
        title = intent.getStringExtra(AppConstants.YOUTUBE_KEYS.TITLE);
        description = intent.getStringExtra(AppConstants.YOUTUBE_KEYS.DESCRIPTION);
        link = intent.getStringExtra(AppConstants.YOUTUBE_KEYS.LINK);

        getSupportActionBar().setTitle(title);


        mActivityYoutubePlayerAcitivityBinding.playTitle.setText(title);
        mActivityYoutubePlayerAcitivityBinding.playDescription.setText(description);

        getLifecycle().addObserver(mActivityYoutubePlayerAcitivityBinding.youtubePlayerView);

        mActivityYoutubePlayerAcitivityBinding.youtubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {

            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String VIDEO_ID = null;
                VIDEO_ID = getVideoId(link);
                if (VIDEO_ID != null) {
                    youTubePlayer.loadVideo(VIDEO_ID, 0);
                }
            }
        });
        mActivityYoutubePlayerAcitivityBinding.youtubePlayerView.addFullScreenListener(new YouTubePlayerFullScreenListener() {
            @Override
            public void onYouTubePlayerEnterFullScreen() {
                getSupportActionBar().hide();
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }

            @Override
            public void onYouTubePlayerExitFullScreen() {
                getSupportActionBar().show();
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}