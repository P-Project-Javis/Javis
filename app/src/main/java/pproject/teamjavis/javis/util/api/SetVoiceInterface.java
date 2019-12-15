package pproject.teamjavis.javis.util.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface SetVoiceInterface {
    @Multipart
    @POST("/dap/app/setVoice")
    Call<ResponseBody> exec(
            @Part("apiId")RequestBody apiID,
            @Part("apiKey")RequestBody apiKey,
            @Part("dbId")RequestBody dbId,
            @Part("voiceId")RequestBody voiceId,
            @Part MultipartBody.Part file);
}
