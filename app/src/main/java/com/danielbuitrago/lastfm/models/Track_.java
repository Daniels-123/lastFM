
package com.danielbuitrago.lastfm.models;

import com.danielbuitrago.lastfm.ui.main.Streamable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Track_ {

    private String name;
    private String duration;
    private String listeners;
    private String mbid;
    private String url;
    private Streamable streamable;
    private Artist_ artist;
    private String artistBD;
    private List<Image> image = null;
    private Attr attr;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */

    /**
     *
     * @param duration
     * @param image
     * @param mbid
     * @param listeners
     * @param streamable
     * @param artist
     * @param name
     * @param attr
     * @param url
     * @param artistBD
     */
    public Track_(String name, String duration, String listeners, String mbid, String url, Streamable streamable, Artist_ artist, List<Image> image, Attr attr, String artistBD) {
        super();
        this.name = name;
        this.duration = duration;
        this.listeners = listeners;
        this.mbid = mbid;
        this.url = url;
        this.streamable = streamable;
        this.artist = artist;
        this.image = image;
        this.attr = attr;
        this.artistBD = artistBD;
    }


    public String getArtistBD() { return artistBD; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Streamable getStreamable() {
        return streamable;
    }

    public void setStreamable(Streamable streamable) {
        this.streamable = streamable;
    }

    public Artist_ getArtist() {
        return artist;
    }

    public void setArtist(Artist_ artist) {
        this.artist = artist;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
