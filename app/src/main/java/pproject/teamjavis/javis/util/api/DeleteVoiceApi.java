package pproject.teamjavis.javis.util.api;

import android.content.Context;

import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import pproject.teamjavis.javis.R;
import pproject.teamjavis.javis.util.item.DeleteVoiceItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteVoiceApi {
    private Context context;
    private String voiceId;
    private boolean isSuccess = false;

    public DeleteVoiceApi(Context context, String voiceId) {
        this.context = context;
        this.voiceId = voiceId;
    }

    public void connect() {
        RequestBody apiId = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_id));
        RequestBody apiKey = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_key));
        RequestBody dbId = RequestBody.create(MediaType.parse("text/plain"), "javis");
        RequestBody voiceId = RequestBody.create(MediaType.parse("text/plain"), this.voiceId);

        final RetroFitConnection connection = new RetroFitConnection();
        Call<DeleteVoiceItem> call = connection.deleteVoice.exec(apiId, apiKey, dbId, voiceId);
        call.enqueue(new Callback<DeleteVoiceItem>() {
            @Override
            public void onResponse(Call<DeleteVoiceItem> call, Response<DeleteVoiceItem> response) {
                Gson gson = new Gson();
                if(response.isSuccessful())
                    isSuccess = true;
                else
                    isSuccess = false;
            }

            @Override
            public void onFailure(Call<DeleteVoiceItem> call, Throwable t) {
                isSuccess = false;
            }
        });
    }

    public boolean isSuccess() {
        return isSuccess;
    }
}
