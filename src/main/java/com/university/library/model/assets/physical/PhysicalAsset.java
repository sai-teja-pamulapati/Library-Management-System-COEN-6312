package com.university.library.model.assets.physical;

import com.university.library.model.assets.Asset;

import java.util.Objects;

public abstract class PhysicalAsset extends Asset {
   
    private String floor;
    private String row;
    private String section;
    private String shelf;

    
    public PhysicalAsset(String assetID, String title, String URLpreview, String URLlogo, Boolean availability, String floor, String row, String section, String shelf) {
        super(null, title, URLpreview, URLlogo, availability);
        this.floor = floor;
        this.row = row;
        this.section = section;
        this.shelf = shelf;
    }
   
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

    @Override
    public String toString() {
        return "Physical{" +
                "floor='" + floor + '\'' +
                ", row='" + row + '\'' +
                ", section='" + section + '\'' +
                ", shelf='" + shelf + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof PhysicalAsset)) return false;
        PhysicalAsset that = (PhysicalAsset) obj;
        return super.equals(obj) && Objects.equals(floor , that.floor) && Objects.equals(row , that.row) && Objects.equals(section , that.section) && Objects.equals(shelf , that.shelf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(floor , row , section , shelf);
    }
}
