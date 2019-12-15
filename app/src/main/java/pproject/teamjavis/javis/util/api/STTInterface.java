package pproject.teamjavis.javis.util.api;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pproject.teamjavis.javis.util.item.STTItem;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface STTInterface {
    @Multipart
    @POST("/api/stt/")
    Call<STTItem> exec(
            @Part("ID")RequestBody id,
            @Part("key")RequestBody key,
            @Part("lang")RequestBody lang,
            @Part("sampling")RequestBody sampling,
            @Part("level")RequestBody level,
            @Part("cmd")RequestBody cmd,
            @Part MultipartBody.Part file);
}
