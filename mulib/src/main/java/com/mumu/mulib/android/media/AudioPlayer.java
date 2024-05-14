package com.mumu.mulib.android.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

public class AudioPlayer {
    private final Context context;
    private final MediaPlayer mediaPlayer;

    private boolean isPlay = false;

    public AudioPlayer(Context context) {
        this.context = context;
        this.mediaPlayer = new MediaPlayer();
        this.mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }


    public void start(String audioFilePath) {
        if (audioFilePath == null) {
            // Audio file path is not set
            return;
        }
        
        try {
            mediaPlayer.setDataSource(context, Uri.parse(audioFilePath));
            mediaPlayer.prepare();
            mediaPlayer.start();
            isPlay = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPlay = false;
        }
    }

    public void stop() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        isPlay = false;
    }

    public void release() {
        mediaPlayer.release();
        isPlay = true;
    }

    public boolean isPlay() {
        return isPlay;
    }
}