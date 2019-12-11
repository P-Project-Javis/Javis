package pproject.teamjavis.javis.util;

import org.json.JSONObject;

import java.util.Map;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

public interface VoiceApi {
    public static final String API_URL = "http://dev-api.maum.ai/dap";
    public static final String apiId = "gachon.pproject.3536baa2f4192";
    public static final String apiKey = "d5df40b1400a4ae1bc53c1539e8a68cd";

    @Multipart
    @Headers({"content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW","cache-control: no-cache","Content-Type: multipart/form-data"})
    @POST("app/setVoice")
    Call<JSONObject> setVoice(@PartMap Map<String, RequestBody> map);

    @Multipart
    @Headers({"content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW","cache-control: no-cache","Content-Type: multipart/form-data"})
    @POST("app/getVoice")
    void getVoice();

    @Multipart
    @Headers({"content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW","cache-control: no-cache","Content-Type: multipart/form-data"})
    @DELETE("app/deleteVoice")
    void DeleteVoice();

    @Multipart
    @Headers({"content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW","cache-control: no-cache","Content-Type: multipart/form-data"})
    @POST("app/recogVoice")
    void RecogVoice();
}
