
package com.southfang.lastfmtest.ui.main;

import java.util.HashMap;
import java.util.Map;

public class Streamable {

    private String text;
    private String fulltrack;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Streamable() {
    }

    /**
     * 
     * @param text
     * @param fulltrack
     */
    public Streamable(String text, String fulltrack) {
        super();
        this.text = text;
        this.fulltrack = fulltrack;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFulltrack() {
        return fulltrack;
    }

    public void setFulltrack(String fulltrack) {
        this.fulltrack = fulltrack;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
