
package com.danielbuitrago.lastfm.models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Artist_ {


    private String name;
    private String listeners;
    private String mbid;
    private String url;
    private String streamable;
    private List<Image> image = null;

    private String imageBD;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Artist_() {
    }

    /**
     *
     * @param imageBD
     * @param image
     * @param mbid
     * @param listeners
     * @param streamable
     * @param name
     * @param url
     */
    public Artist_(String name, String listeners, String mbid, String url, String streamable, List<Image> image, String imageBD) {
        super();
        this.name = name;
        this.listeners = listeners;
        this.mbid = mbid;
        this.url = url;
        this.streamable = streamable;
        this.image = image;
       this.imageBD = imageBD;
    }


    public String getImageBD() {return imageBD;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
