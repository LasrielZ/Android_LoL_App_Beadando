package hu.leagueoflegends.android_api_app_beadando.summinfo;

import android.util.Log;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterForSumm {

    private static final String TAG = "AdapterForSumm";

    String summonerNames;

    public void setSummonerNames(String summonerNames) {
        this.summonerNames = summonerNames;
    }

    private String BASE_URL;
    private Retrofit retrofit;

    public AdapterForSumm(String serverchoice){
        this.BASE_URL = "https://" + serverchoice + ".api.riotgames.com/lol/summoner/v4/summoners/by-name/";
        this.retrofit = buildRetrofitSumm();
    }

    public void getSummData(Callback<ModelForSumm> callback){
        Call<ModelForSumm> call = retrofit.create(InterfaceForSumm.class).getDataForSumm(summonerNames);
        call.enqueue(callback);
    }


    private Retrofit buildRetrofitSumm(){
        Gson gson = SummInfoDeserializer.createGsonBuilderSumm()
                .create();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
