package hu.leagueoflegends.android_api_app_beadando.summinfo;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfaceForSumm {

// fix link rész ->      https://eun1.api.riotgames.com/lol/summoner/v4/
// változó link rész ->  summoners/by-name/AsrielHUN?api_key=RGAPI-93891ff8-dbea-4061-8932-d0a5fb1b51a8

    @GET("{summname}?api_key=RGAPI-3c603b40-fede-47e1-a18c-87b22d7759d3")
    Call<ModelForSumm> getDataForSumm(@Path("summname") String SummName);
}
