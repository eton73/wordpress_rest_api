package com.simbirsoft.dto;

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

    public PostRequest(String date, String date_gmt, String slug, String status, String title,
                       String content, String excerpt, Integer author, Integer featured_media,
                       String comment_status, String ping_status, Boolean sticky, String template,
                       String format, Integer[] categories) {
        this.date = date;
        this.date_gmt = date_gmt;
        this.slug = slug;
        this.status = status;
        this.title = title;
        this.content = content;
        this.excerpt = excerpt;
        this.author = author;
        this.featured_media = featured_media;
        this.comment_status = comment_status;
        this.ping_status = ping_status;
        this.sticky = sticky;
        this.template = template;
        this.format = format;
        this.categories = categories;
    }

    public PostRequest() {
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDate_gmt(String date_gmt) {
        this.date_gmt = date_gmt;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public void setFeatured_media(Integer featured_media) {
        this.featured_media = featured_media;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public void setPing_status(String ping_status) {
        this.ping_status = ping_status;
    }

    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public void setCategories(Integer[] categories) {
        this.categories = categories;
    }

    public String getDate() {
        return date;
    }

    public String getDate_gmt() {
        return date_gmt;
    }

    public String getSlug() {
        return slug;
    }

    public String getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public Integer getAuthor() {
        return author;
    }

    public Integer getFeatured_media() {
        return featured_media;
    }

    public String getComment_status() {
        return comment_status;
    }

    public String getPing_status() {
        return ping_status;
    }

    public Boolean getSticky() {
        return sticky;
    }

    public String getTemplate() {
        return template;
    }

    public String getFormat() {
        return format;
    }

    public Integer[] getCategories() {
        return categories;
    }

}
