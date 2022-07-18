package com.zofa.mastermind;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vv = findViewById(R.id.videoView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent x = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(x);
                finish();
            }
        },5000);

        String video_url = "android.resource://" + MainActivity.this.getPackageName()+"/" + R.raw.abcd;
        final VideoView videoView = findViewById(R.id.videoView);
        Uri videoUri = Uri.parse(video_url);
        MediaController mediaController= new MediaController(MainActivity.this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.start();
    }
}