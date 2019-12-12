package pproject.teamjavis.javis.util.api;

import okhttp3.ResponseBody;
import pproject.teamjavis.javis.util.item.TTSItem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface TTSInterface {
    @Headers("Content-Type:application/json")
    @POST("/tts/stream")
    Call<ResponseBody> exec(@Body TTSItem body);
}
