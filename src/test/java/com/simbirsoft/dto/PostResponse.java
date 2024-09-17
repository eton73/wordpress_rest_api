package com.simbirsoft.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PostResponse {
    private Integer id;
    private String date;
    @JsonProperty("date_gmt")
    private String dateGmt;
    private String name;

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getDateGmt() {
        return dateGmt;
    }
}
