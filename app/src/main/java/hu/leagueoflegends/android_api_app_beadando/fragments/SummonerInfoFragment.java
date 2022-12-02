package hu.leagueoflegends.android_api_app_beadando.fragments;

import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import hu.leagueoflegends.android_api_app_beadando.R;
import hu.leagueoflegends.android_api_app_beadando.models.JsonChampionsResponse;
import hu.leagueoflegends.android_api_app_beadando.summinfo.InterfaceForSumm;
import hu.leagueoflegends.android_api_app_beadando.summinfo.ModelForSumm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SummonerInfoFragment extends Fragment {

    /*View view;
    TextView summNameTxt;
    TextView summLvlTxt;
    TextView summServerTxt;
    ImageView summImg;

    private static final String TAG = "SummonerInfoFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_summoner_info, container, false);

        summNameTxt = view.findViewById(R.id.SummName_textView);
        summLvlTxt = view.findViewById(R.id.SummLvl_textView);
        summServerTxt = view.findViewById(R.id.SummServer_textView);
        summImg = view.findViewById(R.id.SummIcon_imageView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eun1.api.riotgames.com/lol/summoner/v4/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        InterfaceForSumm interfaceForSumm = retrofit.create(InterfaceForSumm.class);
        Call<ModelForSumm> callSumm = interfaceForSumm.getDataForSumm();

        callSumm.enqueue(new Callback<ModelForSumm>() {
            @Override
            public void onResponse(Call<ModelForSumm> call, Response<ModelForSumm> response) {
                if (response.code() != 200){
                    summNameTxt.setText("Check the Connection. API Key maybe?");
                    return;
                }

                String idk = "";
                idk = "IconID" + response.body().getProfileIconId();
                Log.d(TAG, "onResponse: " + idk);

                ModelForSumm result = response.body();
                //Log.d(TAG, "onResponse: " + result.getSummonerLevel() +  "https://ddragon.leagueoflegends.com/cdn/12.22.1/img/profileicon/" + result.getProfileIconId() + ".png" );
                Log.d(TAG, "onResponse: Hello im here");
                int lvl = result.getSummonerLevel();
                summLvlTxt.setText("" + result.getSummonerLevel());
                Glide.with(getActivity()).load("https://ddragon.leagueoflegends.com/cdn/12.22.1/img/profileicon/" + result.getProfileIconId() + ".png" ).into(summImg);

            }

            @Override
            public void onFailure(Call<ModelForSumm> call, Throwable t) {

            }
        });



        getParentFragmentManager().setFragmentResultListener("dataFrom1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String data = result.getString("df1");
                String data2 = result.getString("df2");
                summNameTxt.setText(data);
                summServerTxt.setText(data2);
                //TextView textView = view.findViewById(R.id.dataFrom1);
                //textView.setText(data + data2);
            }
        });

        return view;
    }*/
}