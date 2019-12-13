package pproject.teamjavis.javis.util;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

public class PlayManager {
    private Context context;
    private File file;
    private MediaPlayer player;

    public PlayManager(Context context, String fileName) {
        this.context = context;
        file = new File(Environment.getExternalStorageDirectory(), "/Javis/" + fileName + ".wav");
        player = new MediaPlayer();
        try {
            player.setDataSource(file.getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        player.prepareAsync();
        player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.stop();
                mp.release();
            }
        });
    }
}
