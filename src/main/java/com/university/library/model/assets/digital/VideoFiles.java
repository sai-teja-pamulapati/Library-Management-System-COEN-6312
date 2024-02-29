package com.university.library.model.assets.digital;

public class VideoFiles extends DigitalAsset {
    private String language;
    private int duration;

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
