package pproject.teamjavis.javis.util.api;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pproject.teamjavis.javis.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetVoiceApi {
    private Context context;
    private String fileName;
    private File file;
    private RequestBody requestFile;
    private MultipartBody.Part uploadFile;
    private boolean isSuccess = false;

    public SetVoiceApi(Context context, String fileName) {
        this.context = context;
        this.fileName = fileName;
        file = new File(Environment.getExternalStorageDirectory(), "/Javis/" + fileName + ".wav");
        requestFile = RequestBody.create(MediaType.parse("multipart/from-data"), file);
        uploadFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    public void connect() {
        RequestBody apiId = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_id));
        RequestBody apiKey = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_key));
        RequestBody dbId = RequestBody.create(MediaType.parse("text/plain"), "???");
        RequestBody voiceId = RequestBody.create(MediaType.parse("text/plain"), "javis" + fileName);

        final RetroFitConnection connection = new RetroFitConnection();
        Call<ResponseBody> call = connection.setVoice.exec(apiId, apiKey, dbId, voiceId, uploadFile);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                isSuccess = true;
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                isSuccess = false;
            }
        });
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
