

package com.southfang.lastfmtest.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tracks {

    private List<Track_> track = null;
    private Attr_ attr;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */

    /**
     * 
     * @param track
     * @param attr
     */
    public Tracks(List<Track_> track, Attr_ attr) {
        super();
        this.track = track;
        this.attr = attr;
    }

    public List<Track_> getTrack() {
        return track;
    }

    public void setTrack(List<Track_> track) {
        this.track = track;
    }

    public Attr_ getAttr() {
        return attr;
    }

    public void setAttr(Attr_ attr) {
        this.attr = attr;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
