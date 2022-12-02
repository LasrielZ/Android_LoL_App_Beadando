package hu.leagueoflegends.android_api_app_beadando.models;

public class Images {
    private String full;
    private String sprite;
    //String group;
    //int x;
    //int y;
    //int w;
    //int h;


    public String getFull() {
        return full;
    }

    public String getSprite() {
        return sprite;
    }

    public Images(String full, String sprite) {
        this.full = full;
        this.sprite = sprite;
    }
}
