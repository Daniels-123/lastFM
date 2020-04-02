
package com.southfang.lastfmtest.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Topartists {

    private List<Artist_> artist = null;
    private Attr attr;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Topartists() {
    }

    /**
     * 
     * @param artist
     * @param attr
     */
    public Topartists(List<Artist_> artist, Attr attr) {
        super();
        this.artist = artist;
        this.attr = attr;
    }

    public List<Artist_> getArtist() {
        return artist;
    }

    public void setArtist(List<Artist_> artist) {
        this.artist = artist;
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
