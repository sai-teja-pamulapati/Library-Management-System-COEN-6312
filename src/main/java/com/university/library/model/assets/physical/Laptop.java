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
        return "Laptop Details: \n" +
                "assetId : " + getAssetId() + '\n' +
                "brand : " + brand + '\n' +
                "model : " + model + '\n' +
                "processor : " + processor + '\n' +
                "ram : " + ram + '\n' +
                "storage : " + storage + '\n' +
                "displaySize : " + displaySize + '\n' +
                "weight : " + weight + '\n' +
                "floor : " + getFloor() + '\n' +
                "row : " + getRow() + '\n' +
                "section : " + getSection() + '\n' +
                "shelf : " + getShelf() + '\n' +
                "title : " + getTitle() + '\n' +
                "preview : " + getPreview() + '\n' +
                "logo : " + getLogo() + '\n' +
                "availability : " + isAvailable();
    }
}
