package uce.edu.ec.ProyectoApiNasa.descargas;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import uce.edu.ec.ProyectoApiNasa.model.MarsPhoto;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DatabaseManager {
    private static final String API_KEY = "c1vdp816cGvkK9mZAsfyrljkyYvdJYAvtaDvUS7G";
    private static final String API_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos";

    public static void createNewTable() {
    }

    public List<MarsPhoto> getAllMarsPhotos() throws IOException, InterruptedException, URISyntaxException {
        HttpClient client = HttpClient.newHttpClient();
        Gson gson = new Gson();
        List<MarsPhoto> allPhotos = new ArrayList<>();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(API_URL + "?sol=1000&api_key=" + API_KEY))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 200) {
            JsonObject jsonObject = gson.fromJson(response.body(), JsonObject.class);
            List<MarsPhoto> photos = gson.fromJson(jsonObject.getAsJsonArray("photos"), new TypeToken<List<MarsPhoto>>() {
            }.getType());

            allPhotos.addAll(photos);
        } else {
            System.err.println("Error retrieving photos. Status code: " + response.statusCode());
        }

        return Collections.unmodifiableList(allPhotos);
    }
}
