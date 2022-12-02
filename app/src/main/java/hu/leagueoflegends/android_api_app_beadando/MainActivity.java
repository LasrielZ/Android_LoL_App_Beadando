package hu.leagueoflegends.android_api_app_beadando;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import hu.leagueoflegends.android_api_app_beadando.databinding.ActivityMainBinding;
import hu.leagueoflegends.android_api_app_beadando.fragments.ChampionsFragment;
import hu.leagueoflegends.android_api_app_beadando.fragments.HomeFragment;
import hu.leagueoflegends.android_api_app_beadando.fragments.QuizFragment;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.champions:
                    replaceFragment(new ChampionsFragment());
                    break;
                case R.id.quiz:
                    replaceFragment(new QuizFragment());
                    break;
            }

            return true;
        });
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
    }
}