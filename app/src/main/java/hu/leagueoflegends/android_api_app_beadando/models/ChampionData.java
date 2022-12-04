package hu.leagueoflegends.android_api_app_beadando.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ChampionData {
    public String version;
    public String id;
    public String key;
    public String name;
    public String title;
    public String blurb;

    @SerializedName("image")
    private Images images;

    public ArrayList<String> tags;
    public String partype;

    public ChampionData(String version, String id, String key, String name, String title, String blurb, Images images, ArrayList<String> tags, String partype) {
        this.version = version;
        this.id = id;
        this.key = key;
        this.name = name;
        this.title = title;
        this.blurb = blurb;
        this.images = images;
        this.tags = tags;
        this.partype = partype;
    }

    public String getVersion() {
        return version;
    }

    public String getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getBlurb() {
        return blurb;
    }

    public Images getImages() {
        return images;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public String getPartype() {
        return partype;
    }

}
