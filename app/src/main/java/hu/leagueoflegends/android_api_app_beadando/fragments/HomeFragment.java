package hu.leagueoflegends.android_api_app_beadando.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import hu.leagueoflegends.android_api_app_beadando.R;
import hu.leagueoflegends.android_api_app_beadando.summinfo.SummInfo_Activity;

public class HomeFragment extends Fragment {


    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        String[] servers = new String[] {"eun1", "euw1", "na1"};

        final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                servers);

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.listItems_AutoCompleteTxtView);
        autoCompleteTextView.setAdapter(adapter);

        EditText summSearchEditText = view.findViewById(R.id.edtTxtView);
        Button searchButton = view.findViewById(R.id.btnSearch);
        AutoCompleteTextView listItem_txtView = view.findViewById(R.id.listItems_AutoCompleteTxtView);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), SummInfo_Activity.class);
                intent.putExtra("SERVERCHOICE", listItem_txtView.getText().toString());
                intent.putExtra("NAMEDATA", summSearchEditText.getText().toString());

                startActivity(intent);
            }
        });

        return view;
    }
}
