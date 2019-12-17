package pproject.teamjavis.javis.util.api;

import okhttp3.RequestBody;
import pproject.teamjavis.javis.util.item.DeleteVoiceItem;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.Part;

public interface DeleteVoiceInterface {
    @Multipart
    @Headers("Content-Type: multipart/form-data")
    @DELETE("/dap/app/deleteVoice")
    Call<DeleteVoiceItem> exec(
            @Part RequestBody apiId,
            @Part RequestBody apiKey,
            @Part RequestBody dbId,
            @Part RequestBody voiceId);
}