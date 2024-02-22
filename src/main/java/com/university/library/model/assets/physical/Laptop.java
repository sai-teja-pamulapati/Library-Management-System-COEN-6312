package com.university.library.model.assets.physical;

public class Laptop extends Physical{
    private String brand;
    private String model;
    private String processor;
    private String ram;
    private String storage;
    private String displaySize;
    private String weight;

    @Override
    public String toString() {
        return "Laptop{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", processor='" + processor + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", displaySize='" + displaySize + '\'' +
                ", weight='" + weight + '\'' +
                ", floor='" + getFloor() + '\'' +
                ", row='" + getRow() + '\'' +
                ", section='" + getSection() + '\'' +
                ", shelf='" + getShelf() + '\'' +
                ", assetId='" + getAssetId() + '\'' +
                ", title='" + getTitle() + '\'' +
                ", preview='" + getPreview() + '\'' +
                ", logo='" + getLogo() + '\'' +
                ", availability=" + isAvailable() +
                '}';
    }
}
