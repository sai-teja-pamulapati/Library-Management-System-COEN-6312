package com.university.library.model.assets.digital;

import java.util.Date;

public class AudioBook extends DigitalAsset {
    private String narrator;
    private int duration;
    private Date releasedate;

    public String getNarrator() {
        return narrator;
    }

    public void setNarrator(String narrator) {
        this.narrator = narrator;
    }

    public int getDuration() {
        return duration;
    }

    public void getDuration(int duration) {
        this.duration = duration;
    }

    public Date getReleaseDate() {
        return releasedate;
    }

    public void setReleaseDate(Date releasedate) {
        this.releasedate = releasedate;
    }

    @Override

    public String toString() {
        return "AudioBook [narrator " + narrator +
                ", duration = " + duration +
                ", releaseDate = " + releasedate + "]";
    }

}
