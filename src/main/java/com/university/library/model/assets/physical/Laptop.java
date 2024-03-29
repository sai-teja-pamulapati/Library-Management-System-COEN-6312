package com.university.library.model.assets.physical;

import java.util.Objects;

public class Laptop extends PhysicalAsset {
    private String serialNumber;
    private String brand;
    private String modelNumber;
    private String make;
    private String processor;
    private String ram;

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
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
        this.modelNumber = model;
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
                "model : " + modelNumber + '\n' +
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Laptop laptop = (Laptop) obj;
        return super.equals(obj) && Objects.equals(serialNumber , laptop.serialNumber) && Objects.equals(brand , laptop.brand) && Objects.equals(modelNumber , laptop.modelNumber) && Objects.equals(make , laptop.make) && Objects.equals(processor , laptop.processor) && Objects.equals(ram , laptop.ram) && Objects.equals(storage , laptop.storage) && Objects.equals(displaySize , laptop.displaySize) && Objects.equals(weight , laptop.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNumber , brand , modelNumber , make , processor , ram , storage , displaySize , weight);
    }
}
