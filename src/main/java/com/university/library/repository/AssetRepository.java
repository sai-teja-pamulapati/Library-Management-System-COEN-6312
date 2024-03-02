package com.university.library.repository;

import com.university.library.model.assets.Asset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class AssetRepository {

    private static final AtomicInteger assetIdGenerator = new AtomicInteger(0);

    private static AssetRepository instance;
    private HashMap<String, Asset> assets = new HashMap<>();

    private AssetRepository() {
    }

    public static synchronized AssetRepository getInstance() {
        if (instance == null) {
            instance = new AssetRepository();
        }
        return instance;
    }

    private boolean exists(String assetId) {
        return assets.containsKey(assetId);
    }

    public boolean addAsset(Asset asset) {
        asset.setAssetId(String.valueOf(assetIdGenerator.getAndIncrement()));
        assets.put(asset.getAssetId(), asset);
        return true;
    }

    public void displayAsstes(){
        System.out.println("Following is the list of assets");
        System.out.println("******************************************************************************************");
        for (Map.Entry<String, Asset> entry : assets.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
;        }
        System.out.println("******************************************************************************************");
    }
    public Asset getAsset(String assetId) {
        return assets.get(assetId);
    }

    public List<Asset> getAllAssets() {
        return assets.values().stream().toList();
    }

    public Asset removeAsset(String assetId) {
        return assets.remove(assetId);
    }

    public void update(Asset asset) {
        assets.put(asset.getAssetId(), asset);
    }
}
