package pproject.teamjavis.javis.util.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroFitConnection {
    String URL = "https://api.maum.ai/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public STTInterface stt = retrofit.create(STTInterface.class);
    public TTSInterface tts = retrofit.create(TTSInterface.class);
}
