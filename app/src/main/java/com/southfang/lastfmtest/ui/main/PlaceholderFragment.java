package com.southfang.lastfmtest.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.southfang.lastfmtest.R;
import com.southfang.lastfmtest.adapters.ArtistCustomAdapter;
import com.southfang.lastfmtest.adapters.TrackCustomAdapter;
import com.southfang.lastfmtest.dataBase.DBHandler;
import com.southfang.lastfmtest.models.Artist_;
import com.southfang.lastfmtest.models.Track_;
import com.southfang.lastfmtest.retrofitCalls;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    Button buttonPrev;
    Button buttonNext;
    TextView pageCounterlbl;
    DBHandler db;
    private int pageCounterAuthor;
    private int pageCounterTrack;
    retrofitCalls rfc;
    private ArtistCustomAdapter mAdapter;
    private TrackCustomAdapter mtAdapter;
    private Integer maxPageArtist;
    private Integer maxPageTrack;


    private static final String ARG_SECTION_NUMBER = "section_number";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        pageCounterAuthor = 0;
        pageCounterTrack = 0;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        db = new DBHandler(this.getContext());

        final ListView trackList = root.findViewById(R.id.tracklist);
        final ListView artistList = root.findViewById(R.id.artistList);
         final ProgressBar progBar = root.findViewById(R.id.progressBar);

        pageCounterlbl = root.findViewById(R.id.PageCounterLbl);

        progBar.setVisibility(View.VISIBLE);
        rfc = new retrofitCalls(this.getContext(),db,artistList,trackList, pageCounterAuthor,pageCounterTrack);
        rfc.getArtist();
        rfc.getTrackList();
        progBar.setVisibility(View.INVISIBLE);

        buttonPrev = root.findViewById(R.id.prevButt);
        buttonNext = root.findViewById(R.id.nextButt);
        ActualizarDatosAutor(artistList, pageCounterAuthor);
        UpdateDataTrack(trackList,pageCounterTrack);


        pageViewModel.getText().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer s) {


                if(s==1){
                    trackList.setVisibility(View.GONE);
                    artistList.setVisibility(View.VISIBLE);

                    maxPageArtist = 0;
                    buttonPrev.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if(pageCounterAuthor <= 0){
                                pageCounterAuthor = 0;
                                pageCounterlbl.setText("Page "+ pageCounterAuthor +"");
                                ActualizarDatosAutor(artistList, pageCounterAuthor);
                            }else {
                                pageCounterAuthor--;
                                pageCounterlbl.setText("Page "+ pageCounterAuthor +"");
                                ActualizarDatosAutor(artistList, pageCounterAuthor);
                            }
                        }
                    });

                    buttonNext.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            progBar.setVisibility(View.VISIBLE);
                            pageCounterAuthor++;
                            pageCounterlbl.setText("Page "+ pageCounterAuthor +"");
                            if(pageCounterAuthor > maxPageArtist){
                              try {
                                  List<Artist_> artist = new ArrayList<Artist_>();
                                  mAdapter = new ArtistCustomAdapter(v.getContext(), (ArrayList<Artist_>) artist);
                                  artistList.setAdapter(mAdapter);
                                  maxPageArtist++;
                                  rfc = new retrofitCalls(v.getContext(), db, artistList, trackList, pageCounterAuthor, pageCounterTrack);
                                  rfc.getArtist();
                              }catch (Exception e){Log.d("err","error de red");}
                                ActualizarDatosAutor(artistList, pageCounterAuthor);
                            }else{
                                ActualizarDatosAutor(artistList, pageCounterAuthor);
                            }
                            progBar.setVisibility(View.INVISIBLE);
                        }
                    });


                }else {
                    artistList.setVisibility(View.GONE);
                    trackList.setVisibility(View.VISIBLE);


                    maxPageTrack = 0;
                    buttonPrev.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            if(pageCounterTrack <= 0){
                                pageCounterTrack = 0;
                                pageCounterlbl.setText("Page "+ pageCounterTrack +"");
                                UpdateDataTrack(trackList, pageCounterTrack);
                            }else {
                                pageCounterTrack--;
                                pageCounterlbl.setText("Page "+ pageCounterTrack +"");
                                UpdateDataTrack(trackList, pageCounterTrack);
                            }
                        }
                    });

                    buttonNext.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            progBar.setVisibility(View.VISIBLE);
                            pageCounterTrack++;
                            pageCounterlbl.setText("Page "+ pageCounterTrack +"");
                            if(pageCounterTrack > maxPageTrack){
                                List<Track_> track = new ArrayList<Track_>();
                                mtAdapter = new TrackCustomAdapter(v.getContext(), (ArrayList<Track_>) track);
                                trackList.setAdapter(mtAdapter);
                                maxPageTrack++;
                                rfc = new retrofitCalls(v.getContext(),db,artistList,trackList, pageCounterAuthor,pageCounterTrack);                                rfc.getTrackList();
                                UpdateDataTrack(trackList, pageCounterTrack);
                            }else{
                                UpdateDataTrack(trackList, pageCounterTrack);
                            }
                            progBar.setVisibility(View.INVISIBLE);

                        }
                    });





                }
            }
        });
        return root;
    }

    private void ActualizarDatosAutor(ListView Lister, int page) {
        Log.d("MainActivity", "Reading artists ( ͡ʘ ͜ʖ ͡ʘ)");
        List<Artist_> artist = db.getAllArtists(page);
        mAdapter = new ArtistCustomAdapter(this.getContext(), (ArrayList<Artist_>) artist);
        Lister.setAdapter(mAdapter);
    }

    private void UpdateDataTrack(ListView Lister, int page) {
        Log.d("MainActivity", "Reading tracks ( ͡ʘ ͜ʖ ͡ʘ)");
        List<Track_> track = db.getAllTracks(page);
        mtAdapter = new TrackCustomAdapter(this.getContext(), (ArrayList<Track_>) track);
        Lister.setAdapter(mtAdapter);
    }



}