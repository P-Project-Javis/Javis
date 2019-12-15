package pproject.teamjavis.javis.util.api;

import android.content.Context;
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
        DeleteVoiceItem request = new DeleteVoiceItem();
        request.setApiId(context.getString(R.string.api_id));
        request.setApiKey(context.getString(R.string.api_key));
        request.setDbId("???");
        request.setVoiceId(voiceId);

        final RetroFitConnection connection = new RetroFitConnection();
        Call<ResponseBody> call = connection.deleteVoice.exec(request);
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
