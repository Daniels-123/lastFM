package com.southfang.lastfmtest.apiCall;


import com.southfang.lastfmtest.models.Artist;
import com.southfang.lastfmtest.models.Track;



import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface Api {

    String BASE_URL = "https://ws.audioscrobbler.com/2.0/";

    @GET
    Call<Artist> getTopArtist(@Url String url);


    @GET
    Call<Track> getTracks(@Url String url);

}