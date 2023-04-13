package com.steash.spotifyStats.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.steash.spotifyStats.dtos.artist.SaveArtistDto;
import org.json.*;


public class SpotifyApiService {

    private static final String SPOTIFY_API_URL = "https://api.spotify.com/v1/artists/";

    public static SaveArtistDto getArtistJson(String artistId, String accessToken) throws IOException {
        URL url = new URL(SPOTIFY_API_URL + artistId);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println(in);

            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            JSONObject jsonObj = new JSONObject(response.toString());
            String name = jsonObj.getString("name");
            String id = jsonObj.getString("id");

//            System.out.println("Name: " + name);
//            System.out.println("ID: " + id);

            SaveArtistDto saveArtistDto = new SaveArtistDto();
            saveArtistDto.setName(name);
            saveArtistDto.setSpotifyId(id);

//            System.out.println(response);

            return saveArtistDto;
        } else {
            throw new IOException("Failed to retrieve artist data from Spotify API. Response code: " + responseCode);
        }
    }
}