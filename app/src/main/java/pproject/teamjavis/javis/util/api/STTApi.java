package pproject.teamjavis.javis.util.api;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;

import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pproject.teamjavis.javis.R;
import pproject.teamjavis.javis.util.item.STTItem;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class STTApi {
    private Context context;
    private File file;
    private RequestBody requestFile;
    private MultipartBody.Part uploadFile;
    private String result;

    public STTApi(Context context, String fileName) {
        this.context = context;
        file = new File(Environment.getExternalStorageDirectory(), "/Javis/" + fileName + ".wav");
        requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        uploadFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    public void connect() {
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_id));
        RequestBody key = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_key));
        RequestBody lang = RequestBody.create(MediaType.parse("text/plain"), "kor");
        RequestBody sampling = RequestBody.create(MediaType.parse("text/plain"), "16000");
        RequestBody level = RequestBody.create(MediaType.parse("text/plain"), "baseline");
        RequestBody cmd = RequestBody.create(MediaType.parse("text/plain"), "runFileStt");

        RetroFitConnection connection = new RetroFitConnection();
        Call<STTItem> call = connection.stt.exec(id, key, lang, sampling, level, cmd, uploadFile);
        call.enqueue(new Callback<STTItem>() {
            @Override
            public void onResponse(Call<STTItem> call, Response<STTItem> response) {
                Gson gson = new Gson();
                if(response.isSuccessful()) {
                    result = parser(response.body().toString());
                }
                else {
                    result = "서버와 연결되었지만 오류가 발생했습니다.";
                }
            }

            @Override
            public void onFailure(Call<STTItem> call, Throwable t) {
                result = "서버와의 연결에 실패했습니다.";
            }
        });
    }

    public String getResult() {
        return result;
    }

    private String parser(String data) {
        String[] parsed1 = data.split(",");
        String parsed2 = parsed1[2].substring(6, parsed1[2].length() - 1);
        return parsed2;
    }
}
