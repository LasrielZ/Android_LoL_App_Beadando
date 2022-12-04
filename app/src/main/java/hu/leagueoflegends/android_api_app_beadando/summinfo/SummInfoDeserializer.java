package hu.leagueoflegends.android_api_app_beadando.summinfo;

import android.view.Display;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class SummInfoDeserializer {
    public static GsonBuilder createGsonBuilderSumm() {
        JsonDeserializer<ModelForSumm> deserializerSumm = new JsonDeserializer<ModelForSumm>() {
            @Override
            public ModelForSumm deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                Gson gsonSumm = new Gson();

                ModelForSumm modelForSummDes = new ModelForSumm();
                JsonObject rootJsonObjecSumm = json.getAsJsonObject();

                modelForSummDes.name = rootJsonObjecSumm.get("name").getAsString();
                modelForSummDes.profileIconId = rootJsonObjecSumm.get("profileIconId").getAsInt();
                modelForSummDes.summonerLevel= rootJsonObjecSumm.get("summonerLevel").getAsInt();

                return modelForSummDes;
            }
        };

        GsonBuilder gsonBuilderSumm = new GsonBuilder();
        gsonBuilderSumm.registerTypeAdapter(ModelForSumm.class, deserializerSumm);

        return gsonBuilderSumm;
    }
}
