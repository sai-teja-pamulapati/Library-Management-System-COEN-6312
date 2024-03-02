package com.university.library.model.assets.digital;

public class DigitalBook extends DigitalAsset {

    private int booksize;

    public int getBookSize() {
        return booksize;
    }

    public void setBookSize(int booksize) {
        this.booksize = booksize;
    }

    @Override
    public String toString() {
        return "VideoFile [booksize = " + booksize + "]";
    }

}
