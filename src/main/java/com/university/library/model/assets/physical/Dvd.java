package com.university.library.model.assets.physical;

public class Dvd extends PhysicalAsset {
    private String storage;
    private String condition;
    private String manufacturer;

    public String getstorage() {
        return storage;
    }

    public void setstorage(String storage) {
        this.storage = storage;
    }

    public String getcondition() {
        return condition;
    }

    public void setcondition(String condition) {
        this.condition = condition;
    }

    public String getmanufacturer() {
        return manufacturer;
    }

    public void setmanufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public String toString() {
        return "DVD [storage " + storage +
                ", condition= " + condition +
                ", manufacturer= " + manufacturer + "]";
    }
}
