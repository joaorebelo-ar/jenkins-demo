package com.jrebelo.jenkins.config;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DemoConfiguration extends Configuration {

    private int defaultValue;

    @JsonProperty
    public int getDefaultValue(){
        return defaultValue;
    }

    @JsonProperty
    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
    
}