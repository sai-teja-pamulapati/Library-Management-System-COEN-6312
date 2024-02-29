package com.university.library.model.assets.physical;
import java.util.List;

public class Stationery extends PhysicalAsset {
    private String brand;
    private String dimensions;
    private String color;
    private List<String> contents;
    private String category;
    private String SKU;

    public Stationery(java.lang.String assetID , java.lang.String title , java.lang.String URLpreview , java.lang.String URLlogo , Boolean availability , java.lang.String floor , java.lang.String row , java.lang.String section , java.lang.String shelf) {
        super(assetID , title , URLpreview , URLlogo , availability , floor , row , section , shelf);
    }

    @Override
    public String toString(){
        return "Stationery [brand " + brand + 
        ", dimensions = " + dimensions + 
        ", category = " + category +
         " color = " + color + 
         " SKU = " + SKU + 
         " contents = " + contents + "]";
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getContents() {
        return contents;
    }

    public void setContents(List<String> contents) {
        this.contents = contents;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSKU() {
        return SKU;
    }

    public void setSKU(String SKU) {
        this.SKU = SKU;
    }
}
