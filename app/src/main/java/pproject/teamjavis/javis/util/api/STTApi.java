package pproject.teamjavis.javis.util.api;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

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
    Context context;
    File file;
    RequestBody requestFile;
    MultipartBody.Part uploadFile;

    public STTApi(Context context, String fileName) {
        this.context = context;
        file = new File(Environment.getExternalStorageDirectory(), "/Javis/" + fileName + ".wav");
        requestFile = RequestBody.create(MediaType.parse("multipart/from-data"), file);
        uploadFile = MultipartBody.Part.createFormData("file", file.getName(), requestFile);
    }

    public String connect() {
        String result;

        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_id));
        RequestBody key = RequestBody.create(MediaType.parse("text/plain"), context.getString(R.string.api_key));
        RequestBody lang = RequestBody.create(MediaType.parse("text/plain"), "kor");
        RequestBody sampling = RequestBody.create(MediaType.parse("text/plain"), "16000");
        RequestBody level = RequestBody.create(MediaType.parse("text/plain"), "baseline");
        RequestBody cmd = RequestBody.create(MediaType.parse("text/plain"), "runFileStt");

        RetroFitConnection connection = new RetroFitConnection();
        Call<STTItem> call = connection.stt.sttExec(id, key, lang, sampling, level, cmd, uploadFile);
        call.enqueue(new Callback<STTItem>() {
            @Override
            public void onResponse(Call<STTItem> call, Response<STTItem> response) {
                Gson gson = new Gson();
                if(response.isSuccessful()) {
                    //result = response.body().toString();
                    Toast.makeText(context, "성공 " + response.body().toString(), Toast.LENGTH_LONG).show();

                }
                else {
                    //result[0] = "서버와 연결되었지만 오류가 발생했습니다.";
                    Toast.makeText(context, "실패", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<STTItem> call, Throwable t) {
                //result[0] = "서버와의 연결에 실패했습니다.";
                Toast.makeText(context, "실패", Toast.LENGTH_LONG).show();
            }
        });

        return "";
    }
}
