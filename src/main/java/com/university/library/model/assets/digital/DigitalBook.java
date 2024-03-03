package com.university.library.model.assets.digital;

import java.util.Date;

public class DigitalBook extends DigitalAsset {

    private int bookSize;
    private String isbn;
    private String publisher;
    private Date published;
    private String author;
    private String subject;
    private String description;

    @Override
    public String toString() {
        return "VideoFile [booksize = " + bookSize + "]";
    }

}
