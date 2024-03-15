package com.university.library.action;

import com.university.library.model.assets.Asset;
import com.university.library.model.assets.digital.NewsLetter;
import com.university.library.repository.AssetRepository;

import java.text.SimpleDateFormat;
import java.util.List;

public class ViewNews {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MMM yyyy");

    public static void viewNewsletters() {
        AssetRepository assetRepository = AssetRepository.getInstance();
        List<Asset> allAssets = assetRepository.getAllAssets();

        System.out.println("Available Newsletters: \n");
        allAssets.stream()

                .filter(asset -> asset instanceof NewsLetter)
                .map(asset -> (NewsLetter) asset)
                .forEach(newsletter -> {
                    System.out.println(newsletter);
                    System.out.println("******************************************************************************************");
                });

        if (allAssets.stream().noneMatch(asset -> asset instanceof NewsLetter)) {
            System.out.println("No newsletters available at the moment.");
        }
    }
}
