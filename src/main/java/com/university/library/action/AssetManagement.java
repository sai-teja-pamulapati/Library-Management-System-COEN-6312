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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class AssetManagement {

    private static AssetManagement instance;
    private static Scanner scanner = new Scanner(System.in);
    private AssetRepository assetRepository = AssetRepository.getInstance();
    private LoanAssetRepository loanAssetRepository = LoanAssetRepository.getInstance();

    private AssetManagement() {
    }

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
        List<Asset> resultsForISBN = getResultsForISBN(searchWord);
        printResults(resultsForISBN);
        String requestedAssetId = scanner.nextLine();
        processCheckout(resultsForISBN, requestedAssetId);
    }

    private void printResults(List<Asset> searchedAssets) {
        for (int i = 0; i < searchedAssets.size(); i++) {
            System.out.println("******************************************************************************************");
            Asset resultAsset = searchedAssets.get(i);
            if (resultAsset.isAvailable()) {
                System.out.println(resultAsset);
            }
        }
        System.out.println("******************************************************************************************");
        System.out.println("Please enter the asset id that you want to Borrow: ");
    }

    public List<Asset> getResultsForISBN(String searchWord) {
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<Asset> assetSubList = allAssets.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book) asset).collect(Collectors.toList());
        List<String> assetStringArray = assetSubList.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book) asset)
                .map(Book::getIsbn).collect(Collectors.toList());
        return searchWithGivenParameters(searchWord , assetStringArray , assetSubList);
    }

    public LoanAsset processCheckout(List<Asset> searchedAssets , String requestedAssetId) {
        Optional<Asset> requestedAssetOptional = searchedAssets.stream().filter(Objects::nonNull).filter(asset -> Objects.equals(asset.getAssetId(), requestedAssetId)).findFirst();
        if (requestedAssetOptional.isEmpty()) {
            System.out.println("Requested Object does not exist");
        }
        Asset requestedAsset = requestedAssetOptional.get();
        return requestedAsset.loanAsset();
    }

    private void searchByKeywords() {
        System.out.println("Please Enter the Keyword to search");
        String searchWord = scanner.nextLine();
        List<Asset> resultsForKeywords = getResultsForKeywords(searchWord);
        printResults(resultsForKeywords);
        String requestedAssetId = scanner.nextLine();
        processCheckout(resultsForKeywords, requestedAssetId);
    }

    public List<Asset> getResultsForKeywords(String searchWord) {
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<Asset> assetSubList = allAssets.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book) asset).collect(Collectors.toList());
        List<String> assetStringArray = assetSubList.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book) asset)
                .map(Book::toString).collect(Collectors.toList());
        return searchWithGivenParameters(searchWord , assetStringArray, assetSubList);
    }

    private void searchByAuthor() {
        System.out.println("Please Enter the Author of the book you want to search");
        String searchWord = scanner.nextLine();
        List<Asset> resultsForAuthor = getResultsForAuthor(searchWord);
        printResults(resultsForAuthor);
        String requestedAssetId = scanner.nextLine();
        processCheckout(resultsForAuthor, requestedAssetId);
    }

    public List<Asset> getResultsForAuthor(String searchWord) {
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<Asset> assetSubList = allAssets.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book) asset).collect(Collectors.toList());
        List<String> assetStringArray = assetSubList.stream().filter(Objects::nonNull)
                .filter(asset -> asset instanceof Book).map(asset -> (Book) asset)
                .map(Book::getAuthor).collect(Collectors.toList());
        return searchWithGivenParameters(searchWord , assetStringArray, assetSubList);
    }

    private void searchByTitle() {
        System.out.println("Please Enter the Title of the book you want to search");
        String searchWord = scanner.nextLine();
        List<Asset> resultsForTitle = getResultsForTitle(searchWord);
        printResults(resultsForTitle);
        String requestedAssetId = scanner.nextLine();
        processCheckout(resultsForTitle, requestedAssetId);
    }

    public List<Asset> getResultsForTitle(String searchWord) {
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<String> assetStringArray = allAssets.stream().filter(Objects::nonNull)
                .map(Asset::getTitle).collect(Collectors.toList());
        return searchWithGivenParameters(searchWord , assetStringArray, allAssets);
    }

    private void search() {
        System.out.println("Please Enter your keyword followed by Enter to search");
        String searchWord = scanner.nextLine();
        List<Asset> resultsForSearch = getResultsForSearch(searchWord);
        printResults(resultsForSearch);
        String requestedAssetId = scanner.nextLine();
        processCheckout(resultsForSearch, requestedAssetId);
    }

    public List<Asset> getResultsForSearch(String searchWord) {
        List<Asset> allAssets = assetRepository.getAllAssets();
        List<String> assetStringArray = allAssets.stream().filter(Objects::nonNull)
                .map(Asset::toString).collect(Collectors.toList());
        return searchWithGivenParameters(searchWord , assetStringArray, allAssets);
    }

    private List<Asset> searchWithGivenParameters(String searchWord, List<String> assetStringArray, List<Asset> assetList) {
        List<ExtractedResult> extractedResults = FuzzySearch.extractSorted(searchWord, assetStringArray, 50);

        List<Integer> indexesOfResults = extractedResults.stream().filter(Objects::nonNull).map(ExtractedResult::getIndex).collect(Collectors.toList());
        return indexesOfResults.stream().map(assetList::get).collect(Collectors.toList());
    }

    public void getBorrowingHistory() {
        User user = App.getLoggedInUser();
        printAndGetBorrowingHistory(user);
    }

    public List<LoanAsset> printAndGetBorrowingHistory(User user) {
        List<LoanAsset> loanedItemsForUser = loanAssetRepository.getLoanedItemsForUser(user.getUserId());
        System.out.println();
        System.out.println("                                 Borrowing History");
        for (int i = 0; i < loanedItemsForUser.size(); i++) {
            System.out.println("******************************************************************************************");
            LoanAsset loanAsset = loanedItemsForUser.get(i);
            Asset asset = assetRepository.getAsset(loanAsset.getAssetId());
            System.out.println(asset);
            System.out.println(loanAsset);
        }
        System.out.println("******************************************************************************************");
        return loanedItemsForUser;
    }

    // Asset: book functinalities

    public void addBook() {
        System.out.println("Enter Book's title");
        String title = scanner.nextLine();
        System.out.println("Enter URL for Book preview");
        String urlPreview = scanner.nextLine();
        System.out.println("Enter URL for Book's logo");
        String urlLogo = scanner.nextLine();
        Boolean availability = true;
        System.out.println("Enter Book's ISBN");
        String ISBN = scanner.nextLine();
        System.out.println("Enter Book's Publisher");
        String publisher = scanner.nextLine();
        System.out.println("Enter Published Date in format dd/MM/yyyy");
        String dateStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid Format");
            return;
        }
        System.out.println("Enter Book's Author");
        String author = scanner.nextLine();
        System.out.println("Enter Book's subject");
        String subject = scanner.nextLine();
        System.out.println("Enter Book's Description");
        String description = scanner.nextLine();
        System.out.println("Enter the Floor for Book");
        String floor = scanner.nextLine();
        System.out.println("Enter the Row for Book");
        String row = scanner.nextLine();
        System.out.println("Enter the Section for Book");
        String section = scanner.nextLine();
        System.out.println("Enter the Shelf for Book");
        String shelf = scanner.nextLine();
        addBookToRepository(title, urlPreview, urlLogo, availability, floor, section, row, shelf, ISBN, publisher, date, author, subject, description);

    }

    public Asset addBookToRepository( String title, String urlPreview, String urlLogo, Boolean availability, String floor, String section, String row, String shelf, String ISBN, String publisher, Date date, String author, String subject, String description) {
        Book book = new Book( null,title, urlPreview, urlLogo, availability, floor, section, row, shelf, ISBN, publisher, date, author, subject, description);
        boolean checkAdd = assetRepository.addAsset(book);
        if (checkAdd) {
            System.out.println("Book Added Successfully with ID: " + book.getAssetId());
            return book;
        } else {
            System.out.println("Book addition failed");
            return null;
        }
    }

    public void displayAsstes(){
        List<Asset> allAssets = assetRepository.getAllAssets();
        System.out.println("Following is the list of assets");
        System.out.println("******************************************************************************************");
        for (int i = 0; i < allAssets.size(); i++){
            System.out.println(allAssets.get(i));
        }
        System.out.println("******************************************************************************************");
    }

    public void removeBook() {
        displayAsstes();
        System.out.println("Enter the Book ID to Remove the Book");
        String ID = scanner.nextLine();
        removeBookFromRepository(ID);
        System.out.println("Book removed Successfully");
    }

    public Asset removeBookFromRepository(String ID){
        return assetRepository.removeAsset(ID);
    }

    public void updateBook() {
        displayAsstes();
        System.out.println("Select/Enter Book ID to update it's content");
        String id = scanner.nextLine();
        List<Asset> resultsForSearch = getResultsForSearch(id);
        while (true) {
            try {
                System.out.println("Please Select from the following options"
                        + "Enter 1 to update Title"
                        + "Enter 2 to update preview"
                        + "Enter 3 to update Logo "
                        + "Enter 4 to update ISBN"
                        + "Enter 5 to update Publisher"
                        + "Enter 6 to update Published Date"
                        + "Enter 7 to update Book's Author"
                        + "Enter 8 to update Book's Subject"
                        + "Enter 9 to update Book's Description"
                        + "Enter 10 to update Floor of the book"
                        + "Enter 11 to update Row of the book"
                        + "Enter 12 to update Section of the book"
                        + "Enter 13 to update shelf of the book");
                int select = scanner.nextInt();
                switch (select) {
                    case 1:
                        //assetRepository.update();
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    case 7:
                        break;
                    case 8:
                        break;
                    case 9:
                        break;
                    case 10:
                        break;
                    case 11:
                        break;
                    case 12:
                        break;
                    case 13:
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid Option!");

                }

            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}