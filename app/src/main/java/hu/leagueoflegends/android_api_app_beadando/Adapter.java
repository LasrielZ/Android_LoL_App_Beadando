package hu.leagueoflegends.android_api_app_beadando;

import com.google.gson.Gson;

import hu.leagueoflegends.android_api_app_beadando.models.JsonChampionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Adapter {
    private final String BASE_URL = "https://ddragon.leagueoflegends.com/cdn/12.22.1/data/en_US/";
    private final Retrofit retrofit;

    public Adapter() {
        this.retrofit = buildRetrofit();
    }

    public void getChampions(Callback<JsonChampionsResponse> callback) {
        Call<JsonChampionsResponse> call = retrofit.create(Client.class).getChampions();
        call.enqueue(callback);
    }

    private Retrofit buildRetrofit() {
        Gson gson = Deserializer.createGsonBuilder()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

}
