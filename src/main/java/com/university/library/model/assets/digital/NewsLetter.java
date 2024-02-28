package com.university.library.model.assets.digital;

import java.util.Date;

public class NewsLetter extends DigitalAsset {
    // Attributes
    private Date date;
    //private String content;

    // Constructors
    public NewsLetter() {
        // Default constructor
    }

    public NewsLetter(String accessLink, Date date, String content) {
        super(accessLink); // Call the constructor of the parent class (DigitalAsset)
        this.date = date;
        
    }

    // Getters and setters
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
}
