package com.danielbuitrago.lastfm;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.danielbuitrago.lastfm.adapters.ArtistCustomAdapter;
import com.danielbuitrago.lastfm.adapters.TrackCustomAdapter;
import com.danielbuitrago.lastfm.apiCall.Api;
import com.danielbuitrago.lastfm.dataBase.DBHandler;
import com.danielbuitrago.lastfm.models.Artist;
import com.danielbuitrago.lastfm.models.Artist_;
import com.danielbuitrago.lastfm.models.Image;
import com.danielbuitrago.lastfm.models.Topartists;
import com.danielbuitrago.lastfm.models.Track;
import com.danielbuitrago.lastfm.models.Track_;
import com.danielbuitrago.lastfm.models.Tracks;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class retrofitCalls {

    private final Context mContext;
     DBHandler mdb;
    private Integer mPAgeNumberArtist;
    private Integer mPageNumberTrack;
    ListView mListArtist;
    ListView mListTrack;
    private ArtistCustomAdapter mAdapter;
    private TrackCustomAdapter mtAdapter;

    public retrofitCalls(Context context,DBHandler db, ListView listArtist,ListView ListTrack, Integer pageCounter , Integer PageNumberTrack) {
        super();
        mContext = context;
        mdb = db;
        mListArtist = listArtist;
        mPAgeNumberArtist = pageCounter;
        mListTrack = ListTrack;
        mPageNumberTrack = PageNumberTrack;
    }

    public void getArtist() {
        mdb = new DBHandler(mContext);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);
        String linkBuilder ="?method=geo.gettopartists&country=spain&api_key=829751643419a7128b7ada50de590067&format=json&page="+(mPAgeNumberArtist +1)+"&limit=20";

        Call<Artist> call = api.getTopArtist(linkBuilder);
        call.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                List<Artist_> tempArray = new ArrayList<Artist_>();
                Artist artistList = response.body();
                Topartists a = artistList.getTopartists();
                List<Artist_> b = a.getArtist();
                for (int i = 0;i<b.size();i++){
                    Log.d("MainActivity", "Inserting ..( ͡° ͜ʖ ͡°)");
                    List<Image> c = b.get(i).getImage();
                    mdb.addArtist(new Artist_(b.get(i).getName(),b.get(i).getListeners(),b.get(i).getMbid(),null,null,null,c.get(2).getText()));
                    tempArray.add(new Artist_(b.get(i).getName(),b.get(i).getListeners(),b.get(i).getMbid(),null,null,null,c.get(2).getText()));
                }
                Log.d("MainActivity", "Finished ..( ✧≖ ͜ʖ≖)");

                mAdapter = new ArtistCustomAdapter(mContext, (ArrayList<Artist_>) tempArray);

                if(mListArtist.getCount()==0) {
                    mListArtist.invalidateViews();
                    Log.e("redraw","ths");
                    mListArtist.setAdapter(mAdapter);

                }

            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("error",t.getMessage());
                t.printStackTrace();
            }
        });
    }


    public void getTrackList() {
        mdb = new DBHandler(mContext);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();

        Api api = retrofit.create(Api.class);
        String linkBuilder ="?method=geo.gettoptracks&country=spain&api_key=829751643419a7128b7ada50de590067&format=json&page="+(mPageNumberTrack +1)+"&limit=20";
        Call<Track> call = api.getTracks(linkBuilder);

        call.enqueue(new Callback<Track>() {

            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                List<Track_> tempArray = new ArrayList<Track_>();
                Track tracklist = response.body();
                Tracks a = tracklist.getTracks();
                List<Track_> b = a.getTrack();


                for (int i = 0;i<b.size();i++){
                    Log.d("MainActivity", "Inserting ..( ͡° ͜ʖ ͡°)");
                    Artist_ c = b.get(i).getArtist();

                    mdb.addTrack(new Track_(b.get(i).getName(),b.get(i).getDuration(),b.get(i).getListeners(),null,null,null,null,null,null,c.getName()));
                    tempArray.add(new Track_(b.get(i).getName(),b.get(i).getDuration(),b.get(i).getListeners(),null,null,null,null,null,null,c.getName()));
                }
                Log.d("MainActivity", "Finished ..( ✧≖ ͜ʖ≖)");

                mtAdapter = new TrackCustomAdapter(mContext, (ArrayList<Track_>) tempArray);

                if(mListTrack.getCount()==0) {
                    mListTrack.invalidateViews();
                    Log.e("redraw","ths");
                    mListTrack.setAdapter(mtAdapter);

                }

            }

            @Override
            public void onFailure(Call<Track> call, Throwable t) {
                Toast.makeText(mContext, "No tienes conexion a internet! ", Toast.LENGTH_SHORT).show();
                Log.e("error",t.getMessage());
                t.printStackTrace();
            }
        });
    }


}
