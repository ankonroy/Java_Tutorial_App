package com.example.learnjavafx;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Utill {
    private static String response;

    public static void fetchJson() {
//        String url = "https://api.myjson.online/v1/records/a40799be-7a77-497d-9f89-e46de6fd6ca4";
//        String url = "https://api.myjson.online/v1/records/5cb3b9eb-5b7e-4368-82a1-bd9f61d06046";
        String url = "https://api.myjson.online/v1/records/5210bb7a-fc8c-48be-9ee6-83a6af13b8c6";
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

//            return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void jsonParse(String response) {
        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();

        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject course = jsonArray.get(i).getAsJsonObject();
            System.out.println(course.get("lang").getAsString());
        }
    }

//    public static ArrayList<String> giveNames() {
//        String response = fetchJson();
//
//        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
//        JsonArray jsonArray = jsonObject.get("data").getAsJsonArray();
//
//        ArrayList<String> names = new ArrayList<>();
//
//        for (int i = 0; i < jsonArray.size(); i++) {
//            JsonObject course = jsonArray.get(i).getAsJsonObject();
//            names.add(course.get("lang").getAsString());
//        }
//
//        return names;
//    }



    public static Set<String> giveNames() {
//        String response = fetchJson();

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        jsonObject = jsonObject.get("data").getAsJsonObject();

        Gson gson = new Gson();
        Map<String, Object> jsonMap = gson.fromJson(jsonObject, Map.class);

        // Get the keys (top-level keys)
        Set<String> names = jsonMap.keySet();

        // Print the keys
        System.out.println("Keys: " + names);

        return names;
    }

    public static Set<String> giveTopicNames(String lang) {
//        String response = fetchJson();

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        jsonObject = jsonObject.get("data").getAsJsonObject();
        jsonObject = jsonObject.get(lang).getAsJsonObject();

        Gson gson = new Gson();
        LinkedHashMap<String, Object> jsonMap = gson.fromJson(jsonObject, LinkedHashMap.class);

        // Get the keys (top-level keys)
        Set<String> topics = jsonMap.keySet();

        // Print the keys
        System.out.println("Keys: " + topics);

        return topics;
    }

    public static String giveTopicDetails(String lang, String topic) {
//        String response = fetchJson();

        JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
        jsonObject = jsonObject.get("data").getAsJsonObject();
        jsonObject = jsonObject.get(lang).getAsJsonObject();
        String article = jsonObject.get(topic).getAsString();

        System.out.println(article);

        return article;
    }
}
