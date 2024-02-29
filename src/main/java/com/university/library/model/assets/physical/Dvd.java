package com.university.library.model.assets.physical;

public class Dvd extends PhysicalAsset {
    private String storage;
    private String condition;
    private String manufacturer;

    public Dvd(String assetID , String title , String URLpreview , String URLlogo , Boolean availability , String floor , String row , String section , String shelf) {
        super(assetID , title , URLpreview , URLlogo , availability , floor , row , section , shelf);
    }

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
