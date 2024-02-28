package com.university.library.model.assets.digital;

import java.util.Date;

public class NewsLetter extends DigitalAsset {
    // Attribute for the date
    private Date date;

    // Constructors
    public NewsLetter() {
        // Default constructor
    }

    public NewsLetter(String accessLink, Date date) {
        super(accessLink); // Call the constructor of the parent class (DigitalAsset)
        this.date = date;
    }

    // Getter and setter for the date attribute
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
