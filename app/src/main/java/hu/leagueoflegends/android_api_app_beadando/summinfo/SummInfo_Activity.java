package hu.leagueoflegends.android_api_app_beadando.summinfo;

import android.graphics.drawable.Drawable;
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
    ImageView soloEmblem;
    String summNamesData;
    String serverChoice;
    TextView soloPlacement;
    TextView flexPlacement;
    ImageView flexEmblem;

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
        soloEmblem = findViewById(R.id.SoloIcon_imageView);
        flexPlacement = findViewById(R.id.FlexPlacement_textView);
        flexEmblem = findViewById(R.id.flexIcon_imageView);

        txtSummServer.setText(getIntent().getStringExtra("SERVERCHOICE"));

        summNamesData = getIntent().getStringExtra("NAMEDATA");

        adapterForSumm.setSummonerNames(summNamesData);

        AsyncTaskRunnerSolo tierTaskSolo = new AsyncTaskRunnerSolo();
        tierTaskSolo.execute();

        AsyncTaskRunnerFlex tierTaskFlex = new AsyncTaskRunnerFlex();
        tierTaskFlex.execute();
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

                Glide.with(SummInfo_Activity.this).load("https://ddragon.leagueoflegends.com/cdn/12.22.1/img/profileicon/" + String.valueOf(resultSumm.profileIconId) + ".png").into(imgSummIcon);

            }

            @Override
            public void onFailure(Call<ModelForSumm> call, Throwable t) {

            }
        });
    }


//SOLO RANKED START
    //SOLO RANKED START

    public String soloRankedPlacement(){

        serverChoice = getIntent().getStringExtra("SERVERCHOICE");

        Orianna.setRiotAPIKey("RGAPI-3c603b40-fede-47e1-a18c-87b22d7759d3");

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

    private class AsyncTaskRunnerSolo extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String soloTier = "";
            try {
                soloTier = soloRankedPlacement();

                Log.d("SoloTier - ", "tier: " + soloTier);
            } catch (Exception e) {
                e.printStackTrace();

            }
            return soloTier;
        }

        @Override
        protected void onPostExecute(String rangReceived) {
            super.onPostExecute(rangReceived);

            soloPlacement.setText(rangReceived);

            switch (soloPlacement.getText().toString()){
                case "IRON":
                    soloEmblem.setImageResource(R.drawable.emblem_iron);
                    break;
                case "BRONZE":
                    soloEmblem.setImageResource(R.drawable.emblem_bronze);
                    break;
                case "SILVER":
                    soloEmblem.setImageResource(R.drawable.emblem_silver);
                    break;
                case "GOLD":
                    soloEmblem.setImageResource(R.drawable.emblem_gold);
                    break;
                case "PLATINUM":
                    soloEmblem.setImageResource(R.drawable.emblem_platinum);
                    break;
                case "DIAMOND":
                    soloEmblem.setImageResource(R.drawable.emblem_diamond);
                    break;
                case "MASTER":
                    soloEmblem.setImageResource(R.drawable.emblem_master);
                    break;
                case "GRANDMASTER":
                    soloEmblem.setImageResource(R.drawable.emblem_grandmaster);
                    break;
                case "CHALLENGER":
                    soloEmblem.setImageResource(R.drawable.emblem_challenger);
                    break;
                case "":
                    soloEmblem.setImageResource(R.drawable.emblem_unranked);
                    soloPlacement.setText("UNRANKED");
                    break;
            }
        }
    }

    //SOLO RANKED END
    //SOLO RANKED END


    //FLEX RANKED START
    //FLEX RANKED START

    public String flexRankedPlacement(){
        serverChoice = getIntent().getStringExtra("SERVERCHOICE");

        Orianna.setRiotAPIKey("RGAPI-3c603b40-fede-47e1-a18c-87b22d7759d3");

        if (serverChoice == "euw1"){
            Orianna.setDefaultRegion(Region.EUROPE_WEST);
        }else if (serverChoice == "na1"){
            Orianna.setDefaultRegion(Region.NORTH_AMERICA);
        }else {
            Orianna.setDefaultRegion(Region.EUROPE_NORTH_EAST);
        }

        Summoner summoner = Orianna.summonerNamed(summNamesData).get();

        return summoner.getLeaguePosition(Queue.RANKED_FLEX).getTier().toString();
    }

    private class AsyncTaskRunnerFlex extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String flexTier = "";
            try {
                flexTier = flexRankedPlacement();

                Log.d("FlexTier - ", "tier: " + flexTier);
            } catch (Exception e) {
                e.printStackTrace();

            }
            return flexTier;
        }

        @Override
        protected void onPostExecute(String rangReceived) {
            super.onPostExecute(rangReceived);

            flexPlacement.setText(rangReceived);

            switch (flexPlacement.getText().toString()){
                case "IRON":
                    flexEmblem.setImageResource(R.drawable.emblem_iron);
                    break;
                case "BRONZE":
                    flexEmblem.setImageResource(R.drawable.emblem_bronze);
                    break;
                case "SILVER":
                    flexEmblem.setImageResource(R.drawable.emblem_silver);
                    break;
                case "GOLD":
                    flexEmblem.setImageResource(R.drawable.emblem_gold);
                    break;
                case "PLATINUM":
                    flexEmblem.setImageResource(R.drawable.emblem_platinum);
                    break;
                case "DIAMOND":
                    flexEmblem.setImageResource(R.drawable.emblem_diamond);
                    break;
                case "MASTER":
                    flexEmblem.setImageResource(R.drawable.emblem_master);
                    break;
                case "GRANDMASTER":
                    flexEmblem.setImageResource(R.drawable.emblem_grandmaster);
                    break;
                case "CHALLENGER":
                    flexEmblem.setImageResource(R.drawable.emblem_challenger);
                    break;
                case "":
                    flexEmblem.setImageResource(R.drawable.emblem_unranked);
                    flexPlacement.setText("UNRANKED");
                    break;
            }
        }
    }
}
