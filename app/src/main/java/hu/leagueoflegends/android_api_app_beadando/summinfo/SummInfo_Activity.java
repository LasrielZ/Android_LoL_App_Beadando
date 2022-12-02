package hu.leagueoflegends.android_api_app_beadando.summinfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Queue;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Tier;
import com.merakianalytics.orianna.types.core.summoner.Summoner;

import hu.leagueoflegends.android_api_app_beadando.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SummInfo_Activity extends AppCompatActivity {

    TextView txtSummName;
    TextView txtSummLvl;
    TextView txtSummServer;
    ImageView imgSummIcon;
    String summNamesData;
    String serverChoice;
    String summonerJSON;
    TextView soloPlacement;

    private AdapterForSumm adapterForSumm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.summ_info_activity_layout);

        adapterForSumm = new AdapterForSumm(getIntent().getStringExtra("SERVERCHOICE"));

        txtSummName = findViewById(R.id.SummName_textView);
        txtSummLvl = findViewById(R.id.SummLvl_textView);
        txtSummServer = findViewById(R.id.SummServer_textView);
        imgSummIcon = findViewById(R.id.SummIcon_imageView);
        soloPlacement = findViewById(R.id.SoloPlacement_textView);

        //imgCode = findViewById(R.id.ImgCode_textView);

        txtSummServer.setText(getIntent().getStringExtra("SERVERCHOICE"));

        summNamesData = getIntent().getStringExtra("NAMEDATA");
        adapterForSumm.setSummonerNames(summNamesData);


        AsyncTaskRunner tierTask = new AsyncTaskRunner();
        tierTask.execute();

    }

    @Override
    protected void onStart() {
        super.onStart();

        adapterForSumm.getSummData(new Callback<ModelForSumm>() {
            @Override
            public void onResponse(Call<ModelForSumm> call, Response<ModelForSumm> response) {

                if (response.code() != 200){
                    txtSummName.setText("An error occured, status code: " + response.code());
                    txtSummLvl.setText("Check API Key");
                    return;
                }

                ModelForSumm resultSumm = response.body();
                txtSummName.setText(resultSumm.name);
                txtSummLvl.setText("level: " + String.valueOf(resultSumm.summonerLevel));

                //imgCode.setText(String.valueOf(resultSumm.profileIconId));
                //serverChoiceData = String.valueOf(resultSumm.profileIconId);

                Glide.with(SummInfo_Activity.this).load("https://ddragon.leagueoflegends.com/cdn/12.22.1/img/profileicon/" + String.valueOf(resultSumm.profileIconId) + ".png").into(imgSummIcon);


            }

            @Override
            public void onFailure(Call<ModelForSumm> call, Throwable t) {

            }
        });
    }


    private class AsyncTaskRunner extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String asd = Tier.UNRANKED.toString();
            try {
                asd = rankedPlacement().toString();

                Log.d("SummInfoActivity: ", "tier: " + asd);
            } catch (Exception e) {
                e.printStackTrace();

            }
            return asd;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            soloPlacement.setText(s);
        }
    }


    public String rankedPlacement(){
        serverChoice = getIntent().getStringExtra("SERVERCHOICE");

        Orianna.setRiotAPIKey("RGAPI-8a83c906-cb1c-441e-8a1f-f3d9c59c538c");

        if (serverChoice == "euw1"){
            Orianna.setDefaultRegion(Region.EUROPE_WEST);
        }else if (serverChoice == "na1"){
            Orianna.setDefaultRegion(Region.NORTH_AMERICA);
        }else {
            Orianna.setDefaultRegion(Region.EUROPE_NORTH_EAST);
        }

        Summoner summoner = Orianna.summonerNamed(summNamesData).get();

        return summoner.getLeaguePosition(Queue.RANKED_SOLO).getTier().toString();
    }

}
