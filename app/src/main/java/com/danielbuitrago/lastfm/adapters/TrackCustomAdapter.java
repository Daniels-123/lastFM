package com.danielbuitrago.lastfm.adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.danielbuitrago.lastfm.R;
import com.danielbuitrago.lastfm.models.Track_;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TrackCustomAdapter extends ArrayAdapter<Track_> {

    private Context mContext;
    private List<Track_> trackList = new ArrayList<>();

    public TrackCustomAdapter(@NonNull Context context, ArrayList<Track_> list) {
        super(context, 0 , list);
        mContext = context;
        trackList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.track_list,parent,false);

        Track_ currentTrack = trackList.get(position);

        TextView Name = listItem.findViewById(R.id.NameTrack);
        TextView Artist =  listItem.findViewById(R.id.ArtistTrack);
        TextView Listeners =  listItem.findViewById(R.id.listenersArtist);
        TextView Duration =  listItem.findViewById(R.id.durationTrack);




        Name.setText(currentTrack.getName());
        Listeners.setText("Listeners: "+currentTrack.getListeners());
        Artist.setText("Artist: "+currentTrack.getArtistBD());
        Duration.setText("Duration: "+currentTrack.getDuration());


        return listItem;
    }
}