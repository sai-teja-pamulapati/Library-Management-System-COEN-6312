package com.university.library.model.assets;

public abstract class Asset {
    // Attributes
    private String assetId;
    private String title;
    private String preview;
    private String logo;
    private boolean availability = true;

    // Constructors
    public Asset() {
        // Default constructor
    }

    public Asset(String assetId, String title, String preview, String logo, boolean availability) {
        this.assetId = assetId;
        this.title = title;
        this.preview = preview;
        this.logo = logo;
        this.availability = availability;
    }

    // Getters and setters
    public String getAssetId() {
        return assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public boolean isAvailable() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Asset{" +
                "assetId='" + assetId + '\'' +
                ", title='" + title + '\'' +
                ", preview='" + preview + '\'' +
                ", logo='" + logo + '\'' +
                ", availability=" + availability +
                '}';
    }
}
