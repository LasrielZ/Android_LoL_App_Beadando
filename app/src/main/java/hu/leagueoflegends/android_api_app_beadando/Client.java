package hu.leagueoflegends.android_api_app_beadando;

import hu.leagueoflegends.android_api_app_beadando.models.JsonChampionsResponse;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Client {
    @GET("champion.json")
    Call<JsonChampionsResponse> getChampions();
}
