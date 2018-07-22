package com.example.recoded.news;

public class News {
    private String mNewsTitle;
    private String mNewsText;
    private String mNewsWriter;
    private String mNewsPublishDate;
    private String mNewsUrl;

    private String mNewsImageResourceId = NO_IMAGE_PROVIDED;

    private static final String NO_IMAGE_PROVIDED = "-1";

    public News() {
    } // deafult constructor maybe i will needed

    public String getmNewsText() {
        return mNewsText;
    }

    public void setmNewsText(String mNewsText) {
        this.mNewsText = mNewsText;
    }

    public String getmNewsWriter() {
        return mNewsWriter;
    }

    public void setmNewsWriter(String mNewsWriter) {
        this.mNewsWriter = mNewsWriter;
    }

    public String getmNewsPublishDate() {
        return mNewsPublishDate;
    }

    public void setmNewsPublishDate(String mNewsPublishDate) {
        this.mNewsPublishDate = mNewsPublishDate;
    }

    public String getmNewsImageResourceId() {
        return mNewsImageResourceId;
    }


    public String getmNewsUrl() {
        return mNewsUrl;
    }

    public void setmNewsUrl(String mNewsUrl) {
        this.mNewsUrl = mNewsUrl;
    }

    public News(String NewsTitle, String NewsWriter, String NewsPublishDate, String NewsText, String NewsUrl, String NewsImageResourceId) {

        mNewsTitle = NewsTitle;  // news title
        mNewsText = NewsText;//news description
        mNewsImageResourceId = NewsImageResourceId;// image news
        mNewsWriter = NewsWriter;
        mNewsPublishDate = NewsPublishDate;
        mNewsUrl = NewsUrl;


    }


    public void setmNewsTitle(String mNewsTitle) {
        this.mNewsTitle = mNewsTitle;
    }

    public void setmNewsDescription(String mNewsDescription) {
        this.mNewsText = mNewsDescription;
    }

    public void setmNewsImageResourceId(String mNewsImageResourceId) {
        this.mNewsImageResourceId = mNewsImageResourceId;
    }

    public String getmNewsTitle() {
        return mNewsTitle;
    }


    public String getNewsImageResourceId() {
        return mNewsImageResourceId;
    }


}


