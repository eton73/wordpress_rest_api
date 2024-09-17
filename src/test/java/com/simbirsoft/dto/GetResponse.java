package com.simbirsoft.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetResponse {
    private Integer id;
    private String date;
    @JsonProperty("date_gmt")
    private String dateGmt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getDate() {
        return date;
    }

    public String getDateGmt() {
        return dateGmt;
    }
}
