package pproject.teamjavis.javis.util.api;

import android.content.Context;
import android.os.Environment;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pproject.teamjavis.javis.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecogVoiceApi {
    private Context context;
    private File file;
    private RequestBody requestFile;
    private MultipartBody.Part uploadFile;
    private boolean isSuccess = false;

    public RecogVoiceApi(Context context, String fileName) {
        this.context = context;
        file = new File(Environment.getExternalStorageDirectory(), "/Javis/" + fileName + ".wav");
        requestFile = RequestBody.create(MediaType.parse("multipart/from-data"), file);
        uploadFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    public void connect() {
        RequestBody apiId = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_id));
        RequestBody apiKey = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_key));
        RequestBody dbId = RequestBody.create(MediaType.parse("text/plain"), "???");

        final RetroFitConnection connection = new RetroFitConnection();
        Call<ResponseBody> call = connection.recogVoice.exec(apiId, apiKey, dbId, uploadFile);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful())
                    isSuccess = true;
                else
                    isSuccess = false;
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                isSuccess = false;
            }
        });
    }
}
