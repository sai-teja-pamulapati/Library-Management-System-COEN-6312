package com.university.library.model.assets.digital;

public class Video extends DigitalAsset {
    private String language;
    private int duration;
    private int resolution;
    
    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDuration() {
        return duration;
    }

    public void getDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "VideoFile [duration " + duration +
                ", language = " + language + "]";
    }

}
