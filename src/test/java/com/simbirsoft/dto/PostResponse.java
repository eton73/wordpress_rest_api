package com.simbirsoft.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class PostResponse {
    private Integer id;
    private String date;
    @JsonProperty("date_gmt")
    private String dateGmt;
    private String name;
}
