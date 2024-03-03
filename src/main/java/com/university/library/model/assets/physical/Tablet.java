package com.university.library.model.assets.physical;

public class Tablet extends PhysicalAsset{
    private String serialNumber;
    private String make;
    private String modelNumber;

    public Tablet(String assetID , String title , String URLpreview , String URLlogo , Boolean availability , String floor , String row , String section , String shelf) {
        super(assetID , title , URLpreview , URLlogo , availability , floor , row , section , shelf);
    }
}
