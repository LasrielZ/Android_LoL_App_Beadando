package hu.leagueoflegends.android_api_app_beadando.summinfo;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfaceForSumm {

//https://eun1.api.riotgames.com/lol/summoner/v4/
//        summoners/by-name/AsrielHUN?api_key=RGAPI-93891ff8-dbea-4061-8932-d0a5fb1b51a8

    @GET("{summname}?api_key=RGAPI-6376bc02-0d61-4f76-8162-bd1a6ac9e82c")
    Call<ModelForSumm> getDataForSumm(@Path("summname") String SummName);
}
