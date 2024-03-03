package com.university.library.model.assets.digital;

import java.util.Date;

public class AudioBook extends DigitalAsset {
    private String narrator;
    private int duration;
    private String language;
    private Date releaseDate;

    @Override
    public String toString() {
        return "AudioBook [narrator " + narrator +
                ", duration = " + duration +
                ", releaseDate = " + releaseDate + "]";
    }

}
