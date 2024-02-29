package com.university.library.model.assets.physical;
import java.util.List;

public class Stationery extends PhysicalAsset {
    private String brand;
    private String dimensions;
    private String color;
    private List<String> contents;
    private String category;
    private String SKU;

    public String getBrand(){
        return brand;
    }

    public void String setBrand(String brand){
        this.brand = brand;
    }

    public String getDimensions(){
        return dimensions;
    }

    public void String setDimensions(String dimensions){
        this.dimensions = dimensions;
    }

    public String getColor(){
        return color;
    }

    public void String setColor(String color){
        this.color = color;
    }

    public String getSKU(){
        return color;
    }

    public void String setSKU(String SKU){
        this.SKU = SKU;
    }
    
    public List<contents> getContents(){
        return contents;
    }

    public void List<contents> setContents(List<String> Contents){
        this.contents=contents;
    }

    public String getCategory(){
        return category;
    }
    
    public void setCategory(String category){
        this.category = category;
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

}
