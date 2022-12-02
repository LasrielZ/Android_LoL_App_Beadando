package hu.leagueoflegends.android_api_app_beadando.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import hu.leagueoflegends.android_api_app_beadando.ChampDetailActivity;
import hu.leagueoflegends.android_api_app_beadando.R;
import hu.leagueoflegends.android_api_app_beadando.summinfo.AdapterForSumm;
import hu.leagueoflegends.android_api_app_beadando.summinfo.InterfaceForSumm;
import hu.leagueoflegends.android_api_app_beadando.summinfo.SummInfo_Activity;

public class HomeFragment extends Fragment {

    View view;


    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        String[] servers = new String[] {"EUNE", "EUW", "NA"};
        final ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_dropdown_item_1line,
                servers);

        AutoCompleteTextView autoCompleteTextView = view.findViewById(R.id.autoCompleteTxtView);
        autoCompleteTextView.setAdapter(adapter);

        EditText editText = view.findViewById(R.id.edtTxtView);
        Button button = view.findViewById(R.id.btnSearch);
        TextInputLayout textInputLayout = view.findViewById(R.id.txtInputLayout);
        AutoCompleteTextView autoCompleteTextView1 = view.findViewById(R.id.autoCompleteTxtView);
        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.edtTxtView);

                TextInputLayout textInputLayout = view.findViewById(R.id.txtInputLayout);
                AutoCompleteTextView autoCompleteTextView1 = view.findViewById(R.id.autoCompleteTxtView);

                Bundle result = new Bundle();
                result.putString("df1",editText.getText().toString());

                result.putString("df2", autoCompleteTextView1.getText().toString());

                getParentFragmentManager().setFragmentResult("dataFrom1",result);
                editText.setText("");

                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, new SummonerInfoFragment());
                fragmentTransaction.commit();
            }
        });

        return view;
    }*/
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SummInfo_Activity.class);
                intent.putExtra("SERVERCHOICE", autoCompleteTextView1.getText().toString());
                intent.putExtra("NAMEDATA", editText.getText().toString());
                startActivity(intent);
            }
        });

        return view;
    }
}
