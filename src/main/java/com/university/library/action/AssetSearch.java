package com.university.library.action;

import com.university.library.model.assets.Asset;
import com.university.library.repository.AssetRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AssetSearch {

    private static AssetSearch instance;
    private static Scanner scanner = new Scanner(System.in);
    private static AssetRepository assetRepository = AssetRepository.getInstance();


    private AssetSearch() {}

    public static synchronized AssetSearch getInstance() {
        if (instance == null) {
            instance = new AssetSearch();
        }
        return instance;
    }

    public void browse() {
        while (true) {
            try {
                System.out.print("Choose from the following options\n" +
                        "1. Simple Search\n" +
                        "2. Search by Title\n" +
                        "3. Search by Author\n" +
                        "4. Search by Keywords\n" +
                        "5. Search by ISBN\n" +
                        "6. Pay Fines\n" +
                        "7. Go back\n");
                String searchCommands = scanner.nextLine();
                switch (searchCommands) {
                    case "1":
                        search();
                        break;
                    case "2":
                        searchByTitle();
                        break;
                    case "3":
                        searchByAuthor();
                        break;
                    case "4":
                        searchByKeywords();
                        break;
                    case "5":
                        searchByISBN();
                        break;
                    case "6":
                        payFines();
                        break;
                    case "7":
                        return;
                    default:
                        throw new IllegalArgumentException("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    private void payFines() {

    }

    private void searchByISBN() {

    }

    private void searchByKeywords() {

    }

    private void searchByAuthor() {

    }

    private void searchByTitle() {
    }

    private void search() {
        System.out.print("Please Enter your keyword followed by Enter to search \n" +
                "7. Go back\n");
        String searchWord = scanner.nextLine();
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<String> assetStringArray = allAssets.stream().filter(Objects::nonNull)
                        .map(Asset::toString).collect(Collectors.toList());
        List<ExtractedResult> extractedResults = FuzzySearch.extractSorted(searchWord , assetStringArray, 40);
//        System.out.println(extractedResults);

        List<Integer> indexesOfResults = extractedResults.stream().filter(Objects::nonNull).map(ExtractedResult::getIndex).collect(Collectors.toList());
        List<Asset> searchedAssets = indexesOfResults.stream().map(allAssets::get).collect(Collectors.toList());

        for (int i = 0 ; i < searchedAssets.size() ; i++) {
            System.out.println("*********************************************");
            Asset resultAsset = searchedAssets.get(i);
            System.out.println(resultAsset);
        }
        System.out.println("*********************************************");
        System.out.println("Please enter the asset id that you want to Borrow: ");
        String requestedAssetId = scanner.nextLine();
    }
}
