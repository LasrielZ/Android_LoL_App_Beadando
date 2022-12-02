package hu.leagueoflegends.android_api_app_beadando.summinfo;

import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterForSumm {

    String summonerNames;
    String serverDatas;

    public void setSummonerNames(String summonerNames) {
        this.summonerNames = summonerNames;
    }

    public void setServerDatas(String serverDatas) {
        this.serverDatas = serverDatas;
    }

    private final String BASE_URL = "https://eun1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
    private final Retrofit retrofit;

    public AdapterForSumm(){
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
