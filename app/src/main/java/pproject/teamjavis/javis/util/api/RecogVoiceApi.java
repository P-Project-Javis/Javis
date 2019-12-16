package pproject.teamjavis.javis.util.api;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pproject.teamjavis.javis.R;
import pproject.teamjavis.javis.util.item.RecogVoiceItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecogVoiceApi {
    private Context context;
    private File file;
    private RequestBody requestFile;
    private MultipartBody.Part uploadFile;
    private boolean isSuccess = false;
    private String voiceId = null;

    public RecogVoiceApi(Context context, String fileName) {
        this.context = context;
        file = new File(Environment.getExternalStorageDirectory(), "/Javis/" + fileName + ".wav");
        requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        uploadFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    public void connect() {
        RequestBody apiId = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_id));
        RequestBody apiKey = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_key));
        RequestBody dbId = RequestBody.create(MediaType.parse("text/plain"), "javis");

        final RetroFitConnection connection = new RetroFitConnection();
        Call<RecogVoiceItem> call = connection.recogVoice.exec(apiId, apiKey, dbId, uploadFile);
        call.enqueue(new Callback<RecogVoiceItem>() {
            @Override
            public void onResponse(Call<RecogVoiceItem> call, Response<RecogVoiceItem> response) {
                Gson gson = new Gson();
                if(response.isSuccessful()) {
                    isSuccess = true;
                    voiceId = response.body().toString();
                }
                else {
                    isSuccess = false;
                }
            }

            @Override
            public void onFailure(Call<RecogVoiceItem> call, Throwable t) {
                isSuccess = false;
            }
        });
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getVoiceId() {
        return voiceId;
    }
}
