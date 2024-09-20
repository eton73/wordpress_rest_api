package com.simbirsoft.repository.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

@FieldNameConstants
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class WpPostModel {
    private Integer id;
    private String post_date;
    private String post_date_gmt;
    private String post_status;
    private String post_title;
    private String post_content;
    private String post_excerpt;
    private Integer post_author;
    private String comment_status;
    private String ping_status;
}
