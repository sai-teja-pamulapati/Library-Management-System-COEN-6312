package com.university.library.action;

import com.university.library.App;
import com.university.library.model.LoanAsset;
import com.university.library.model.assets.Asset;
import com.university.library.model.assets.physical.Book;
import com.university.library.model.users.User;
import com.university.library.repository.AssetRepository;
import com.university.library.repository.LoanAssetRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.util.*;
import java.util.stream.Collectors;

public class AssetManagement {

    private static AssetManagement instance;
    private static Scanner scanner = new Scanner(System.in);
    private static AssetRepository assetRepository = AssetRepository.getInstance();
    private static LoanAssetRepository loanAssetRepository = LoanAssetRepository.getInstance();

    private AssetManagement() {}

    public static synchronized AssetManagement getInstance() {
        if (instance == null) {
            instance = new AssetManagement();
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
                        "6. Go back\n");
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
                        return;
                    default:
                        throw new IllegalArgumentException("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    private void searchByISBN() {
        System.out.println("Please Enter the ISBN of the book you want to search");
        String searchWord = scanner.nextLine();
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<Asset> assetSubList = allAssets.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book)asset).collect(Collectors.toList());
        List<String> assetStringArray = assetSubList.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book)asset)
                .map(Book::getIsbn).collect(Collectors.toList());
        searchAndProcessCheckout(searchWord, assetStringArray, assetSubList);
    }

    private void searchByKeywords() {
        System.out.println("Please Enter the Keyword to search");
        String searchWord = scanner.nextLine();
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<Asset> assetSubList = allAssets.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book)asset).collect(Collectors.toList());
        List<String> assetStringArray = assetSubList.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book)asset)
                .map(Book::toString).collect(Collectors.toList());
        searchAndProcessCheckout(searchWord, assetStringArray, assetSubList);
    }

    private void searchByAuthor() {
        System.out.println("Please Enter the Author of the book you want to search");
        String searchWord = scanner.nextLine();
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<Asset> assetSubList = allAssets.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book)asset).collect(Collectors.toList());
        List<String> assetStringArray = assetSubList.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book)asset)
                .map(Book::getAuthor).collect(Collectors.toList());
        searchAndProcessCheckout(searchWord, assetStringArray, assetSubList);
    }

    private void searchByTitle() {
        System.out.println("Please Enter the Title of the book you want to search");
        String searchWord = scanner.nextLine();
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<String> assetStringArray = allAssets.stream().filter(Objects::nonNull)
                .map(Asset::getTitle).collect(Collectors.toList());
        searchAndProcessCheckout(searchWord, assetStringArray, allAssets);
    }

    private void search() {
        System.out.println("Please Enter your keyword followed by Enter to search");
        String searchWord = scanner.nextLine();
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<String> assetStringArray = allAssets.stream().filter(Objects::nonNull)
                        .map(Asset::toString).collect(Collectors.toList());
        searchAndProcessCheckout(searchWord, assetStringArray, allAssets);
    }

    private void searchAndProcessCheckout(String searchWord , List<String> assetStringArray , List<Asset> assetList) {
        List<ExtractedResult> extractedResults = FuzzySearch.extractSorted(searchWord , assetStringArray, 50);
//        System.out.println(extractedResults);

        List<Integer> indexesOfResults = extractedResults.stream().filter(Objects::nonNull).map(ExtractedResult::getIndex).collect(Collectors.toList());
        List<Asset> searchedAssets = indexesOfResults.stream().map(assetList::get).collect(Collectors.toList());

        for (int i = 0 ; i < searchedAssets.size() ; i++) {
            System.out.println("******************************************************************************************");
            Asset resultAsset = searchedAssets.get(i);
            if(resultAsset.isAvailable()){
                System.out.println(resultAsset);
            }
        }
        System.out.println("******************************************************************************************");
        System.out.println("Please enter the asset id that you want to Borrow: ");
        String requestedAssetId = scanner.nextLine();
        Optional<Asset> requestedAssetOptional = assetList.stream().filter(Objects::nonNull).filter(asset -> Objects.equals(asset.getAssetId() , requestedAssetId)).findFirst();
        if(requestedAssetOptional.isEmpty()){
            System.out.println("Requested Object doesnot exist");
            return;
        }
        Asset requestedAsset = requestedAssetOptional.get();
        requestedAsset.loanAsset();
    }

    public void getBorrowingHistory() {
        User user = App.getLoggedInUser();
        List<LoanAsset> loanedItemsForUser = loanAssetRepository.getLoanedItemsForUser(user.getUserId());
        System.out.println();
        for (int i = 0 ; i < loanedItemsForUser.size() ; i++) {
            System.out.println("******************************************************************************************");
            LoanAsset loanAsset = loanedItemsForUser.get(i);
            Asset asset = assetRepository.getAsset(loanAsset.getAssetId());
            System.out.println(asset);
            System.out.println(loanAsset);
        }
        System.out.println("******************************************************************************************");
        System.out.println();

    }
}
