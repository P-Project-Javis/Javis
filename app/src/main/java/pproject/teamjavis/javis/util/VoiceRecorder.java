/*
음성 녹음 클래스

음성을 녹음하는 작업을 담당하는 클래스.
 */
package pproject.teamjavis.javis.util;

import android.media.MediaRecorder;

import java.io.IOException;

public class VoiceRecorder {
    private MediaRecorder recorder;
    private String tempFileName;

    public VoiceRecorder() {
        recorder = null;
        tempFileName = "voiceTemp.mp4";
    }

    public void startRecord() {
        recorder = new MediaRecorder();

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        recorder.setOutputFile(tempFileName);

        try {
            recorder.prepare();
            recorder.start();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecord() {
        if(recorder != null) {
            recorder.stop();
            recorder.release();
            recorder = null;
        }
    }
}
