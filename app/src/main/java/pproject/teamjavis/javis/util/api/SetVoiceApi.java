package pproject.teamjavis.javis.util.api;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pproject.teamjavis.javis.R;
import pproject.teamjavis.javis.util.item.SetVoiceItem;
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
        requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        uploadFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    public void connect() {
        Log.v(this.getClass().getSimpleName(), "SetVoice 실행");
        RequestBody apiId = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_id));
        RequestBody apiKey = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_key));
        RequestBody dbId = RequestBody.create(MediaType.parse("text/plain"), "javis");
        RequestBody voiceId = RequestBody.create(MediaType.parse("text/plain"), fileName);

        final RetroFitConnection connection = new RetroFitConnection();
        Call<SetVoiceItem> call = connection.setVoice.exec(apiId, apiKey, dbId, voiceId, uploadFile);
        call.enqueue(new Callback<SetVoiceItem>() {
            @Override
            public void onResponse(Call<SetVoiceItem> call, Response<SetVoiceItem> response) {
                Gson gson = new Gson();
                if(response.isSuccessful()) {
                    isSuccess = true;
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show();
                }
                else {
                    isSuccess = false;
                    Toast.makeText(context, response.body().toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<SetVoiceItem> call, Throwable t) {
                isSuccess = false;
            }
        });
        Log.v(this.getClass().getSimpleName(), "SetVoice 종료");
    }

    public boolean getIsSuccess() {
        return isSuccess;
    }
}
