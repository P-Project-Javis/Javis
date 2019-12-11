package pproject.teamjavis.javis.util;

import android.os.Environment;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallApi {

    private static final String API_ID = "gachon.pproject.3536baa2f4192";
    private static final String API_KEY = "d5df40b1400a4ae1bc53c1539e8a68cd";
    private static final String DB_ID = "USER";
    private static final String VOICE_ID = "user1";

    VoiceApi voiceApi;


    public void callSetVoiceApi(){
        File file = new File(Environment.getExternalStorageDirectory(), "/Javis");

        RequestBody fileBody = RequestBody.create(MediaType.parse("audio/*"), file);
        RequestBody apiId = RequestBody.create(MediaType.parse("text/plain"), API_ID);
        RequestBody apiKey = RequestBody.create(MediaType.parse("text/plain"), API_KEY);
        RequestBody dbId = RequestBody.create(MediaType.parse("text/plain"), DB_ID);
        RequestBody voiceId = RequestBody.create(MediaType.parse("text/plain"), VOICE_ID);

        Map<String, RequestBody> reqMap = new HashMap<String, RequestBody>();

        reqMap.put("file", fileBody);
        reqMap.put("apiId", apiId);
        reqMap.put("dbId", dbId);
        reqMap.put("apiKey", apiKey);
        reqMap.put("voiceId", voiceId);

        Call<JSONObject> call = voiceApi.setVoice(reqMap);
        call.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable throwable) {

            }
        });
    }
}
