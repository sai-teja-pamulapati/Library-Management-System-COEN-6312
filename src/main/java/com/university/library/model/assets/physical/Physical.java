package com.university.library.model.assets.physical;

public abstract class Physical {
    // Attributes
    private String floor;
    private String row;
    private String section;
    private String shelf;

    // Constructors
    public Physical() {
        // Default constructor
    }

    public Physical(String floor, String row, String section, String shelf) {
        this.floor = floor;
        this.row = row;
        this.section = section;
        this.shelf = shelf;
    }

    // Getters and setters
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }
}
