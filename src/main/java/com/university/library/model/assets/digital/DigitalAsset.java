package com.university.library.model.assets.digital;

import com.university.library.model.assets.Asset;

public abstract class DigitalAsset extends Asset {
    // Attribute
    private String accessLink;

    // Constructors
    public DigitalAsset() {
        // Default constructor
    }

    public DigitalAsset(String accessLink) {
        this.accessLink = accessLink;
    }

    // Getter and setter
    public String getAccessLink() {
        return accessLink;
    }

    public void setAccessLink(String accessLink) {
        this.accessLink = accessLink;
    }
}
