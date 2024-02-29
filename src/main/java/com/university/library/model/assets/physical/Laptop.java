package com.university.library.model.assets.physical;

public class Laptop extends PhysicalAsset {
    private String brand;
    private String model;
    private String processor;
    private String ram;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getDisplaySize() {
        return displaySize;
    }

    public void setDisplaySize(String displaySize) {
        this.displaySize = displaySize;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    private String storage;
    private String displaySize;
    private String weight;

    public Laptop(String assetID , String title , String URLpreview , String URLlogo , Boolean availability , String floor , String row , String section , String shelf , String brand , String model , String processor , String ram , String storage , String displaySize , String weight) {
        super(assetID , title , URLpreview , URLlogo , availability , floor , row , section , shelf);
        this.brand = brand;
        this.model = model;
        this.processor = processor;
        this.ram = ram;
        this.storage = storage;
        this.displaySize = displaySize;
        this.weight = weight;
    }

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
