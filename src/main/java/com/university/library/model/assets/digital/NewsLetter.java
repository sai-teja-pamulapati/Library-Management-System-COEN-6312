package com.university.library.model.assets.digital;

import java.util.Date;

public class NewsLetter extends DigitalAsset {
  
    private Date date;
    

  
    public NewsLetter() {
       
    }

    public NewsLetter(String accessLink, Date date) {
        super(accessLink); // Call the constructor of the parent class (DigitalAsset)
        this.date = date;
        
    }

    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
}
