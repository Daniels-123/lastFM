package com.southfang.lastfmtest.adapters;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.southfang.lastfmtest.R;
import com.southfang.lastfmtest.models.Artist_;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ArtistCustomAdapter extends ArrayAdapter<Artist_> {

    private Context mContext;
    private List<Artist_> artistList = new ArrayList<>();

    public ArtistCustomAdapter(@NonNull Context context,  ArrayList<Artist_> list) {
        super(context, 0 , list);
        mContext = context;
        artistList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.artist_list,parent,false);

        Artist_ currentArtist = artistList.get(position);

        TextView Name = (TextView) listItem.findViewById(R.id.NameArtist);
        TextView Listeners = (TextView) listItem.findViewById(R.id.ListenersArtist);
        ImageView Image = (ImageView) listItem.findViewById(R.id.imageView);



        Name.setText(currentArtist.getName());
        Listeners.setText("Listeners: "+currentArtist.getListeners());
        Picasso.with(mContext).load(currentArtist.getImageBD()).into(Image);


        return listItem;
    }
}