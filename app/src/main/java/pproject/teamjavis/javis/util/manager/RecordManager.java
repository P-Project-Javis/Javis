/*
음성 녹음 클래스

음성을 녹음하는 작업을 담당하는 클래스.
 */
package pproject.teamjavis.javis.util.manager;

import android.media.AudioFormat;
import android.media.MediaRecorder;
import android.os.Environment;

import androidx.annotation.NonNull;

import java.io.File;
import java.io.IOException;

import omrecorder.AudioChunk;
import omrecorder.AudioRecordConfig;
import omrecorder.OmRecorder;
import omrecorder.PullTransport;
import omrecorder.PullableSource;
import omrecorder.Recorder;

public class RecordManager {
    Recorder recorder;
    String fileName;

    public void setupRecorder(String fileName) {
        recorder = OmRecorder.wav(
                new PullTransport.Default(mic(), new PullTransport.OnAudioChunkPulledListener() {
                    @Override
                    public void onAudioChunkPulled(AudioChunk audioChunk) { }
                }), file(fileName)
        );
    }

    public void startRecorder() {
        recorder.startRecording();
    }

    public void stopRecorder() {
        try {
            recorder.stopRecording();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName() {
        return fileName;
    }

    private PullableSource mic() {
        return new PullableSource.Default(
                new AudioRecordConfig.Default(
                        MediaRecorder.AudioSource.MIC, AudioFormat.ENCODING_PCM_16BIT, AudioFormat.CHANNEL_IN_MONO, 16000)
                );
    }

    @NonNull private File file(String fileName) {
        File filePath = new File(Environment.getExternalStorageDirectory(), "/Javis");
        if(!filePath.exists())
            filePath.mkdir();
        this.fileName = fileName;
        return new File(Environment.getExternalStorageDirectory(), "/Javis/" + fileName + ".wav");
    }
}
