/*
음성 녹음 클래스

음성을 녹음하는 작업을 담당하는 클래스.
 */
package pproject.teamjavis.javis.util;

import android.content.Context;
import android.media.AudioFormat;
import android.media.MediaRecorder;

import java.io.File;
import java.io.IOException;

import omrecorder.AudioChunk;
import omrecorder.AudioRecordConfig;
import omrecorder.OmRecorder;
import omrecorder.PullTransport;
import omrecorder.PullableSource;
import omrecorder.Recorder;

public class VoiceRecorder {
    private Recorder recorder;
    private File file;

    public VoiceRecorder(Context context, String fileName) {
        recorder = null;
        file = new File(context.getFilesDir(), fileName + ".wav");

        recorder = OmRecorder.wav(
                new PullTransport.Default(mic(), new PullTransport.OnAudioChunkPulledListener() {
                    @Override
                    public void onAudioChunkPulled(AudioChunk audioChunk) {
                        animateVoice((float)(audioChunk.maxAmplitude() / 200.0f));
                    }
                }), file
        );
    }

    public void startRecord() {
        recorder.startRecording();
    }

    public void stopRecord() {
        try {
            recorder.stopRecording();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PullableSource mic() {
        return new PullableSource.Default(
                new AudioRecordConfig.Default(
                        MediaRecorder.AudioSource.MIC, AudioFormat.ENCODING_PCM_16BIT, AudioFormat.CHANNEL_IN_MONO, 44100
                )
        );
    }

    private void animateVoice(final float maxPeach) { return; }
}
