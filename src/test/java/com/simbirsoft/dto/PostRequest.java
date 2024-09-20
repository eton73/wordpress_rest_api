package com.simbirsoft.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostRequest {
    private String date;
    private String date_gmt;
    private String slug;
    private String status;
    private String title;
    private String content;
    private String excerpt;
    private Integer author;
    private Integer featured_media;
    private String comment_status;
    private String ping_status;
    private Boolean sticky;
    private String template;
    private String format;
    private Integer[] categories;
}
