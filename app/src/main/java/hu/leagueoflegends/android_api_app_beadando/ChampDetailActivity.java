package hu.leagueoflegends.android_api_app_beadando;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ChampDetailActivity extends AppCompatActivity {

    ImageView imageV;
    TextView nameTV;
    TextView titleTV;
    TextView storyTV;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.champion_detail_activity);

        String name = getIntent().getStringExtra("NAME");
        String title = getIntent().getStringExtra("TITLE");
        String picture = getIntent().getStringExtra("PICTURE");

        String story = getIntent().getStringExtra("STORY");

        imageV = findViewById(R.id.imageView_championPic);
        nameTV = findViewById(R.id.textView_championName);
        titleTV = findViewById(R.id.textView_championTitle);
        storyTV = findViewById(R.id.textView_championStory);

        Glide.with(this).load(picture).into(imageV);
        nameTV.setText(name);
        titleTV.setText(title);
        storyTV.setText(story);

    }
}
