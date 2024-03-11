package com.university.library.model.assets.digital;

import java.util.Date;

public class NewsLetter extends DigitalAsset {

    private Date date;
    private String publication;

    public NewsLetter() {
    }

    public NewsLetter(String accessLink, Date date, String publication) {
        super(accessLink);
        setDate(date);
        this.publication = publication;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        if (date.after(new Date())) {
            throw new IllegalArgumentException("Publication date cannot be in the future");
        }
        this.date = date;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    // @Override
    // public String toString() {
    //     SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy");
    //     return "NewsLetter Details: \n" +
    //             "Asset ID: " + getAssetId() + "\n" +
    //             "Publication Date: " + dateFormat.format(date) + "\n" +
    //             "Publication: " + publication + "\n" +
    //             "Access Link: " + getAccessLink() + "\n" ;
    // }
    @Override
    public String toString() {
    return "NewsLetter Details: \n" +
            "Asset ID: " + getAssetId() + "\n" +
            "Publication Date: " + date + "\n" + // Using date's default toString method
            "Publication: " + publication + "\n" +
            "Access Link: " + getAccessLink() + "\n" ;
}
}
