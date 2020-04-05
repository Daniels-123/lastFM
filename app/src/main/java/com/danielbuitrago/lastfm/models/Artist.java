
package com.danielbuitrago.lastfm.models;

import java.util.HashMap;
import java.util.Map;

public class Artist {

    private Topartists topartists;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Artist() {
    }

    /**
     * 
     * @param topartists
     */
    public Artist(Topartists topartists) {
        super();
        this.topartists = topartists;
    }

    public Topartists getTopartists() {
        return topartists;
    }

    public void setTopartists(Topartists topartists) {
        this.topartists = topartists;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
