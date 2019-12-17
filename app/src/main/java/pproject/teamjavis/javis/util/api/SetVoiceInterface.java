package pproject.teamjavis.javis.util.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pproject.teamjavis.javis.util.item.SetVoiceItem;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface SetVoiceInterface {
    @Multipart
    @PUT("/dap/app/setVoice")
    Call<SetVoiceItem> exec(
            @Part("apiId")RequestBody apiId,
            @Part("apiKey")RequestBody apiKey,
            @Part("dbId")RequestBody dbId,
            @Part("voiceId")RequestBody voiceId,
            @Part MultipartBody.Part file);
}
