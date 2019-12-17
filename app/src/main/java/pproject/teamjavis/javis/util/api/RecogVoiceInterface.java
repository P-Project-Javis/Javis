package pproject.teamjavis.javis.util.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pproject.teamjavis.javis.util.item.RecogVoiceItem;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RecogVoiceInterface {
    @Multipart
    @POST("/dap/app/recogVoice")
    Call<RecogVoiceItem> exec(
            @Part("apiId")RequestBody apiId,
            @Part("apiKey")RequestBody apiKey,
            @Part("dbId")RequestBody dbId,
            @Part MultipartBody.Part file);
}
