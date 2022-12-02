package hu.leagueoflegends.android_api_app_beadando.summinfo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import hu.leagueoflegends.android_api_app_beadando.Adapter;
import hu.leagueoflegends.android_api_app_beadando.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SummInfo_Activity extends AppCompatActivity {

    TextView txtSummName;
    TextView txtSummLvl;
    TextView txtSummServer;
    ImageView imgSummIcon;
    String summNamesData;
    String serverChoiceData;

    //TextView imgCode;

    private AdapterForSumm adapterForSumm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.summ_info_activity_layout);

        adapterForSumm = new AdapterForSumm();


        txtSummName = findViewById(R.id.SummName_textView);
        txtSummLvl = findViewById(R.id.SummLvl_textView);
        txtSummServer = findViewById(R.id.SummServer_textView);
        imgSummIcon = findViewById(R.id.SummIcon_imageView);

        //imgCode = findViewById(R.id.ImgCode_textView);

        txtSummServer.setText(getIntent().getStringExtra("SERVERCHOICE"));

        summNamesData = getIntent().getStringExtra("NAMEDATA");
        adapterForSumm.setSummonerNames(summNamesData);

        adapterForSumm.setServerDatas("eun1");

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
                serverChoiceData = String.valueOf(resultSumm.profileIconId);

                Glide.with(SummInfo_Activity.this).load("https://ddragon.leagueoflegends.com/cdn/12.22.1/img/profileicon/" + String.valueOf(resultSumm.profileIconId) + ".png").into(imgSummIcon);


            }

            @Override
            public void onFailure(Call<ModelForSumm> call, Throwable t) {

            }
        });
    }
}
