package pproject.teamjavis.javis.util;

import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PUT;

public interface VoiceApi {

    @Multipart
    @Headers({"content-type: multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW","cache-control: no-cache","Content-Type: multipart/form-data"})
    @PUT("/app/setVoice")
    void setVoice();

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
