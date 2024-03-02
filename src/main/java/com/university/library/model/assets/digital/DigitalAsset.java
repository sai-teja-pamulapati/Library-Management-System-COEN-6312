package com.university.library.model.assets.digital;

import com.university.library.model.assets.Asset;

public abstract class DigitalAsset extends Asset {
   
    private String accessLink;

   
    public DigitalAsset() {
        
    }

    public DigitalAsset(String accessLink) {
        this.accessLink = accessLink;
    }

   
    public String getAccessLink() {
        return accessLink;
    }

    public void setAccessLink(String accessLink) {
        this.accessLink = accessLink;
    }
}
