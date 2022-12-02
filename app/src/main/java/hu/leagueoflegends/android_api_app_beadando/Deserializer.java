package hu.leagueoflegends.android_api_app_beadando;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import hu.leagueoflegends.android_api_app_beadando.models.ChampionData;
import hu.leagueoflegends.android_api_app_beadando.models.JsonChampionsResponse;

public class Deserializer {
    public static GsonBuilder createGsonBuilder() {
        JsonDeserializer<JsonChampionsResponse> deserializer = new JsonDeserializer<JsonChampionsResponse>() {
            @Override
            public JsonChampionsResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                Gson gson = new Gson();

                JsonChampionsResponse championResponse = new JsonChampionsResponse();
                JsonObject rootJsonObject = json.getAsJsonObject();

                championResponse.type = rootJsonObject.get("type").getAsString();
                championResponse.format = rootJsonObject.get("format").getAsString();
                championResponse.version = rootJsonObject.get("version").getAsString();

                JsonObject dataJsonObject = rootJsonObject.getAsJsonObject("data");
                Iterator<String> dataKeys = dataJsonObject.keySet().iterator();
                ;
                List<ChampionData> champions = new ArrayList<>();

                while (dataKeys.hasNext()) {
                    String key = dataKeys.next();
                    if (dataJsonObject.get(key) instanceof JsonObject) {
                        // do something with jsonObject here
                        ChampionData champion = gson.fromJson(dataJsonObject.get(key), ChampionData.class);
                        champions.add(champion);
                    }
                }
                championResponse.champions = champions;

                return championResponse;
            }
        };

        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(JsonChampionsResponse.class, deserializer);

        return gsonBuilder;
    }

}