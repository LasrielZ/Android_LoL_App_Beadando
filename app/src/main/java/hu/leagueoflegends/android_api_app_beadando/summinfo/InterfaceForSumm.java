package hu.leagueoflegends.android_api_app_beadando.summinfo;

import android.widget.TextView;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface InterfaceForSumm {

//https://eun1.api.riotgames.com/lol/summoner/v4/
//        summoners/by-name/AsrielHUN?api_key=RGAPI-93891ff8-dbea-4061-8932-d0a5fb1b51a8

    @GET("{summname}?api_key=RGAPI-8a83c906-cb1c-441e-8a1f-f3d9c59c538c")
    Call<ModelForSumm> getDataForSumm(@Path("summname") String SummName);
}
