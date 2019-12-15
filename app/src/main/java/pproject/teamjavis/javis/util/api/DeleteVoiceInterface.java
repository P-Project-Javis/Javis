package pproject.teamjavis.javis.util.api;

import okhttp3.ResponseBody;
import pproject.teamjavis.javis.util.item.DeleteVoiceItem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface DeleteVoiceInterface {
    @Headers("Content-Type: multipart/form-data")
    @POST("/dap/app/deleteVoice")
    Call<ResponseBody> exec(@Body DeleteVoiceItem body);
}