package com.prj.android.mulib;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mumu.mulib.android.media.AudioPlayer;
import com.mumu.mulib.android.media.MuAudioRecorder;
import com.mumu.mulib.android.window.MuToast;
import com.prj.mulib.R;

public class AudioRecoderActivity extends BaseActivity {
    private Button btnStartRecorder, btnStopRecorder, btnPauseRecorder, btnResumeRecorder, btnPlay;

    private MuAudioRecorder muAudioRecorder;
    private AudioPlayer audioPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_recoder);

        intiView();
        setTopTitle("录音测试");
        start();
        stop();
        pause();
        resume();
        play();

        setBackListener(view -> {
            if (muAudioRecorder.isRecording()){
                MuToast.show(this, "请线停止录音");
            }
        });

        muAudioRecorder = new MuAudioRecorder(getFilesDir()+"/test.aac");

        audioPlayer = new AudioPlayer(this);

    }

    private void intiView(){
        btnStartRecorder = findViewById(R.id.btnStartRecorder);
        btnStopRecorder = findViewById(R.id.btnStopRecorder);
        btnPauseRecorder = findViewById(R.id.btnPauseRecorder);
        btnResumeRecorder = findViewById(R.id.btnResumeRecorder);
        btnPlay = findViewById(R.id.btnPlay);
    }

    private void start(){
        btnStartRecorder.setOnClickListener(view -> {
            if (audioPlayer.isPlay()){
                MuToast.show(this, "请先停止播放");
                return;
            }
            btnStartRecorder.setVisibility(View.GONE);
            btnStopRecorder.setVisibility(View.VISIBLE);
            btnResumeRecorder.setVisibility(View.GONE);
            btnPauseRecorder.setVisibility(View.VISIBLE);
            setTopTitle("录音中...");
            muAudioRecorder.start();
        });
    }

    private void stop(){
        btnStopRecorder.setOnClickListener(view -> {
            btnStartRecorder.setVisibility(View.VISIBLE);
            btnStopRecorder.setVisibility(View.GONE);
            btnResumeRecorder.setVisibility(View.GONE);
            btnPauseRecorder.setVisibility(View.GONE);
            setTopTitle("录音测试");
            muAudioRecorder.stop();
        });
    }

    private void pause(){
        btnPauseRecorder.setOnClickListener(view -> {
            btnStartRecorder.setVisibility(View.GONE);
            btnStopRecorder.setVisibility(View.VISIBLE);
            btnResumeRecorder.setVisibility(View.VISIBLE);
            btnPauseRecorder.setVisibility(View.GONE);
            setTopTitle("录音暂停中...");
            muAudioRecorder.pause();
        });
    }

    private void resume(){
        btnResumeRecorder.setOnClickListener(view -> {
            btnStartRecorder.setVisibility(View.GONE);
            btnStopRecorder.setVisibility(View.VISIBLE);
            btnResumeRecorder.setVisibility(View.GONE);
            btnPauseRecorder.setVisibility(View.VISIBLE);
            setTopTitle("录音中...");
            muAudioRecorder.resume();
        });
    }


    private void play(){
        btnPlay.setOnClickListener(view -> {
            if (muAudioRecorder.isRecording()){
                MuToast.show(this, "请先停止录音");
            } else {
                if (!audioPlayer.isPlay()){
                    audioPlayer.start(getFilesDir()+"/test.aac");
                    btnPlay.setText("停止播放");
                    setTopTitle("播放中...");
                } else {
                    audioPlayer.stop();
                    btnPlay.setText("播放");
                    setTopTitle("录音测试");
                }
            }
        });
    }

}