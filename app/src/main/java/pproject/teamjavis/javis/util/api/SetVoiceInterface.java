package pproject.teamjavis.javis.util.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface SetVoiceInterface {
    @Multipart
    @Headers({"cache-control:no-cache", "Content-Type: multipart/form-data",
            "content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW'"})
    @PUT("/dap/app/setVoice")
    Call<ResponseBody> exec(
            @Part("apiId")RequestBody apiID,
            @Part("apiKey")RequestBody apiKey,
            @Part("dbId")RequestBody dbId,
            @Part("voiceId")RequestBody voiceId,
            @Part MultipartBody.Part file);
}
