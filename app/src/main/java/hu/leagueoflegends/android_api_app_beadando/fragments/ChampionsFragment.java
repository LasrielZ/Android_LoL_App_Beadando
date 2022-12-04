package hu.leagueoflegends.android_api_app_beadando.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import hu.leagueoflegends.android_api_app_beadando.Adapter;
import hu.leagueoflegends.android_api_app_beadando.AdapterForRecyclerView;
import hu.leagueoflegends.android_api_app_beadando.ChampDetailActivity;
import hu.leagueoflegends.android_api_app_beadando.R;
import hu.leagueoflegends.android_api_app_beadando.models.ChampionData;
import hu.leagueoflegends.android_api_app_beadando.models.JsonChampionsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChampionsFragment extends Fragment implements AdapterForRecyclerView.OnItemClickListener {

    View view;

    private Adapter lolAdapter;
    private List<String> championNames;

    private TextView responseTextView;
    private ArrayAdapter<String> arrayAdapter;

    RecyclerView recyclerView;
    List<ChampionData> champDataList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_champions, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        champDataList = new ArrayList<>();
        lolAdapter = new Adapter();
        championNames = new ArrayList<>();
        responseTextView = view.findViewById(R.id.responseTextView);
        arrayAdapter = new ArrayAdapter<String>(getActivity(),  android.R.layout.simple_list_item_1, android.R.id.text1, championNames);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        lolAdapter.getChampions(new Callback<JsonChampionsResponse>() {
            @Override
            public void onResponse(Call<JsonChampionsResponse> call, Response<JsonChampionsResponse> response) {

                if(response.isSuccessful()) {

                    JsonChampionsResponse result = response.body();
                    championNames.clear();
                    champDataList.clear();

                    for (ChampionData champion : result.champions) {
                        championNames.add(champion.name);
                        champDataList.add(champion);
                    }
                    arrayAdapter.notifyDataSetChanged();
                    responseTextView.setText("version: " + result.version);
                    PutDataIntoRecyclerView(champDataList);

                } else {
                    responseTextView.setText("hiba történt, státuszkód: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<JsonChampionsResponse> call, Throwable t) {

            }
        });
    }

    private void PutDataIntoRecyclerView(List<ChampionData> champDataList) {

        AdapterForRecyclerView adapterForRecyclerView = new AdapterForRecyclerView(getActivity(), champDataList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapterForRecyclerView);

    }


    private static final String TAG = "ChampionsFragment";
    @Override
    public void onItemClick(int position) {
        Log.d(TAG, "onItemClick: clicked on " + champDataList.get(position).getName() + " the " + champDataList.get(position).getTitle());
        Log.d(TAG, "image: " + "https://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + champDataList.get(position).getName() + "_0.jpg");

        Intent intent = new Intent(getActivity(), ChampDetailActivity.class);
        intent.putExtra("TITLE", champDataList.get(position).getTitle());
        intent.putExtra("NAME", champDataList.get(position).getName());
        intent.putExtra("PICTURE","https://ddragon.leagueoflegends.com/cdn/img/champion/loading/" + champDataList.get(position).getName() + "_0.jpg");
        intent.putExtra("STORY", champDataList.get(position).getBlurb());

        startActivity(intent);
    }
}