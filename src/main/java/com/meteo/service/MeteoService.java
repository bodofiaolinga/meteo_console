package com.meteo.service;
import com.meteo.model.Meteo;
import org.json.JSONObject;
import java.io.IOException;
import java.net.URI; import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
public class MeteoService {
    private static final String API_KEY = "660ee8daccf50723311e068015a88f9c";
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    public Meteo getMeteo(String city) throws IOException, InterruptedException {
        String url = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric&lang=fr";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 404) {
            throw new RuntimeException("Ville introuvable : " + city);
        } else if (response.statusCode() == 401) {
            throw new RuntimeException("Clé API incorrecte !");
        } else if (response.statusCode() != 200) {
            throw new RuntimeException("Erreur API : code " + response.statusCode());
        }
        JSONObject json = new JSONObject(response.body());
        String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
        double temp = json.getJSONObject("main").getDouble("temp");
        int humidity = json.getJSONObject("main").getInt("humidity");
        double lon= json.getJSONObject("coord").getDouble("lon");
        return new Meteo(description, temp, humidity, city,lon);
    }
}