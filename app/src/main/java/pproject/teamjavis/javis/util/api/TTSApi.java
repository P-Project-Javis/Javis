package pproject.teamjavis.javis.util.api;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;
import pproject.teamjavis.javis.R;
import pproject.teamjavis.javis.util.item.TTSItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TTSApi {
    private Context context;
    private String text;

    public TTSApi(Context context, String text) {
        this.context = context;
        this.text = text;
    }

    public void connect() {
        TTSItem request = new TTSItem();
        request.setApiId(context.getString(R.string.api_id));
        request.setApiKey(context.getString(R.string.api_key));
        request.setText(text);
        request.setVoiceName("baseline_kor");

        RetroFitConnection connection = new RetroFitConnection();
        Call<ResponseBody> call = connection.tts.exec(request);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    writeAudio(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void writeAudio(ResponseBody body) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "/Javis/response.wav");

            InputStream inputStream = body.byteStream();
            OutputStream outputStream = new FileOutputStream(file);

            byte[] fileReader = new byte[4096];
            long fileSize = body.contentLength();
            long fileSizeDownloaded = 0;

            while(true) {
                int read = inputStream.read(fileReader);
                if(read == -1)
                    break;
                outputStream.write(fileReader, 0, read);
                fileSizeDownloaded += read;
            }
            outputStream.flush();

            inputStream.close();
            outputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
