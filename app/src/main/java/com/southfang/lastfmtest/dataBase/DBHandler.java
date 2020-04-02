package com.southfang.lastfmtest.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.southfang.lastfmtest.models.Artist;
import com.southfang.lastfmtest.models.Artist_;
import com.southfang.lastfmtest.models.Image;
import com.southfang.lastfmtest.models.Track_;

import java.util.ArrayList;
import java.util.List;


public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;


    private static final String DATABASE_NAME = "LastFm";


    private static final String TABLE_ARTIST = "Artist";
    private static final String TABLE_TRACK = "Track";

    private static final String artistIdArtist = "artistId";
    private static final String artistName = "artistName";
    private static final String artistListeners = "artistListeners";
    private static final String artistImage = "artistImage";

    private static final String TrackId = "TrackId";
    private static final String TrackArtist = "TrackArtist";
    private static final String trackPlayCount = "trackPlayCount";
    private static final String trackName = "trackName";
    private static final String trackDuration = "trackDuration";


    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_ARTIST_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_ARTIST + "("
                + artistIdArtist + " TEXT PRIMARY KEY, "
                + artistName + " TEXT, "
                + artistImage + " TEXT, "
                + artistListeners + " TEXT" + ")";

        String CREATE_TRACK_TABLE = "CREATE TABLE IF NOT EXISTS "
                + TABLE_TRACK + "("
                + TrackId + " TEXT PRIMARY KEY  , "
                + TrackArtist + " TEXT, "
                + trackPlayCount + " TEXT, "
                + trackDuration + " TEXT, "
                + trackName + " TEXT" + ")";

        db.execSQL(CREATE_ARTIST_TABLE);
        db.execSQL(CREATE_TRACK_TABLE);
    }





    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRACK);
        onCreate(db);
    }


    public void addArtist(Artist_ artist) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(artistIdArtist, artist.getMbid());
        values.put(artistName, artist.getName());
        values.put(artistListeners, artist.getListeners());
        values.put(artistImage, artist.getImageBD());

        db.insert(TABLE_ARTIST, null, values);
        db.close();
    }




    public void addTrack(Track_ track) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TrackArtist, track.getArtistBD());
        values.put(trackName, track.getName());
        values.put(trackPlayCount, track.getListeners());
        values.put(trackDuration, track.getDuration());
        values.put(TrackId, track.getName()+track.getArtistBD());
        db.insert(TABLE_TRACK, null, values);
        db.close();
    }




    public List<Track_> getAllTracks(int page) {
        List<Track_> trackListG = new ArrayList<Track_>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TRACK + " LIMIT "+ 20 +" OFFSET "+ page*20;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor!=null && cursor.getCount()>0){
            // Looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Track_ trc = new Track_(
                    cursor.getString(4),
                            cursor.getString(3),
                            cursor.getString(2),
                    cursor.getString(0), null,
                            null,
                            null,
                            null,
                            null,
                            cursor.getString(1)
                    );
                    trackListG.add(trc);
                } while (cursor.moveToNext());
            }
        }else{
            trackListG.clear();
            return trackListG;
        }
        return trackListG;
    }





    public List<Artist_> getAllArtists(int page) {
        List<Artist_> artistList = new ArrayList<Artist_>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ARTIST + " LIMIT "+ 20 +" OFFSET "+ page*20;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor!=null && cursor.getCount()>0){
            // Looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Artist_ art = new Artist_(

                            cursor.getString(1),
                            cursor.getString(3),
                            cursor.getString(0),
                            null,
                            null,
                            null, cursor.getString(2)
                    );
                    artistList.add(art);
                } while (cursor.moveToNext());
            }
        }else{
            artistList.clear();
            return artistList;
        }
        return artistList;
    }







}
