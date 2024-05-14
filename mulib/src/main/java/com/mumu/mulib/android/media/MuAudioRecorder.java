package com.mumu.mulib.android.media;

import android.media.MediaRecorder;

import java.io.IOException;

/**
 * @Author: MuMu
 * @Date: 2024/5/9 14:26
 */
public class MuAudioRecorder {
    private MediaRecorder mediaRecorder;
    private final String outputFile;
    private boolean isRecording = false;

    public MuAudioRecorder(String outputFile) {
        this.outputFile = outputFile;
    }

    public void start(){
        if (isRecording) return;
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        mediaRecorder.setOutputFile(outputFile);
        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
            isRecording = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void pause() {
        if (mediaRecorder==null) return;
        if (isRecording) {
            mediaRecorder.pause();
            isRecording = false;
        }
    }

    public void resume() {
        if (mediaRecorder==null) return;
        if (!isRecording) {
            mediaRecorder.resume();
            isRecording = true;
        }
    }


    public void stop(){
        if (mediaRecorder!=null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            isRecording = false;
        }
    }


    public double getAmplitude() {
        if (mediaRecorder != null && isRecording) {
            return mediaRecorder.getMaxAmplitude();
        } else {
            return 0;
        }
    }

    public double getDecibel() {
        double amplitude = getAmplitude();
        if (amplitude > 0) {
            return 20 * Math.log10(amplitude / 32767.0);
        } else {
            return 0;
        }
    }

    public boolean isRecording() {
        return isRecording;
    }
}
