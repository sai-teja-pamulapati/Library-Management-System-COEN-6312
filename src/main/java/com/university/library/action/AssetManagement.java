package com.university.library.action;

import com.university.library.App;
import com.university.library.model.LoanAsset;
import com.university.library.model.assets.Asset;
import com.university.library.model.assets.digital.DigitalBook;
import com.university.library.model.assets.digital.NewsLetter;
import com.university.library.model.assets.physical.Book;
import com.university.library.model.assets.physical.Laptop;
import com.university.library.model.users.User;
import com.university.library.model.users.academic.Admin;
import com.university.library.model.users.academic.Librarian;
import com.university.library.model.users.academic.Staff;
import com.university.library.model.users.academic.Student;
import com.university.library.model.users.nonacademic.FreeUser;
import com.university.library.model.users.nonacademic.PaidUser;
import com.university.library.repository.AssetRepository;
import com.university.library.repository.LoanAssetRepository;
import me.xdrop.fuzzywuzzy.FuzzySearch;
import me.xdrop.fuzzywuzzy.model.ExtractedResult;

import java.text.MessageFormat;
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
        User user = App.getLoggedInUser();
        Optional<Asset> requestedAssetOptional = searchedAssets.stream().filter(Objects::nonNull).filter(asset -> Objects.equals(asset.getAssetId(), requestedAssetId)).findFirst();

        if (requestedAssetOptional.isEmpty()) {
            System.out.println("Requested Object does not exist");
            return null;
        }
        Asset requestedAsset = requestedAssetOptional.get();

        int borrowingLimit = getBorrowingLimit(requestedAsset, user);
        long activeLoans = loanAssetRepository.countActiveLoansByUserId(user.getUserId());

        if (borrowingLimit != -1 && activeLoans >= borrowingLimit) {
            System.out.println(MessageFormat.format("You have reached your borrowing limit of \"{0}\" {1}s. Please return an item to borrow a new one." , borrowingLimit, requestedAsset.getClass().getSimpleName()));
            return null;
        }

        return requestedAsset.loanAsset();
    }

    private int getBorrowingLimit(Asset requestedAsset , User user) {
        if (requestedAsset instanceof Laptop){
            if (user instanceof Librarian){
                return 2;
            } else if (user instanceof Admin){
                return 2;
            } else if (user instanceof Staff){
                return 2;
            } else if (user instanceof Student){
                return 1;
            } else if (user instanceof PaidUser){
                return 0;
            } else if (user instanceof FreeUser){
                return 0;
            }
        } else if (requestedAsset instanceof Book) {
            if (user instanceof Librarian){
                return 100;
            } else if (user instanceof Admin){
                return 50;
            } else if (user instanceof Staff){
                return 50;
            } else if (user instanceof Student){
                return 10;
            } else if (user instanceof PaidUser){
                return 10;
            } else if (user instanceof FreeUser){
                return 0;
            }
        } else if (requestedAsset instanceof DigitalBook){
            if (user instanceof PaidUser){
                return 20;
            } else if (user instanceof FreeUser){
                return 0;
            } else {
                return -1;
            }
        }
        return 0;
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
           // System.out.println("Press " + asset.getAssetId() + " to return");
        }
        System.out.println("******************************************************************************************");
        return loanedItemsForUser;
    }

    // Asset: book functinalities

    public void addBook() {
        String title = getInputFromUser("Enter Book's title");
        String urlPreview = getInputFromUser("Enter URL for Book preview");
        String urlLogo = getInputFromUser("Enter URL for Book logo");
        String isbn = getInputFromUser("Enter Book's ISBN");
        String publisher = getInputFromUser("Enter Book's Publisher");
        String author = getInputFromUser("Enter Book's Author");
        String subject = getInputFromUser("Enter Book's subject");
        String description = getInputFromUser("Enter Book's Description");
        String floor = getInputFromUser("Enter the Floor for Book");
        String row = getInputFromUser("Enter the Row for Book");
        String section = getInputFromUser("Enter the Section for Book");
        String shelf = getInputFromUser("Enter the Shelf for Book");
        Date date;
        while (true){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                String dateStr = getInputFromUser("Enter Published Date in format dd/MM/yyyy");
                date = sdf.parse(dateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid Format");
            }
        }

        addBookToRepository(title, urlPreview, urlLogo, true , floor, section, row, shelf, isbn, publisher, date, author, subject, description);

    }

    private String getInputFromUser(String printMessage) {
        while (true){
            System.out.println(printMessage);
            String input = scanner.nextLine();
            if (input != null && !input.isEmpty()) {
                return input;
            }
            System.out.println("Please enter a valid detail");
            System.out.println("******************************************************************************************");
        }
    }

    public void addEBook(){
        String isbn = getInputFromUser("Enter Book's ISBN");
        String publisher = getInputFromUser("Enter Book's Publisher");
        String author = getInputFromUser("Enter Book's Author");
        String subject = getInputFromUser("Enter Book's subject");
        String description = getInputFromUser("Enter Book's Description");
        int bookSize = 0;
        Date date;
        while (true){
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            try {
                String dateStr = getInputFromUser("Enter Published Date in format dd/MM/yyyy");
                date = sdf.parse(dateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Invalid Format");
            }
        }
        while(true){
            System.out.println("Enter Book's size");
            bookSize = scanner.nextInt();
            if (bookSize > 30 || bookSize == 0){
                System.out.println("The size of a book to upload cannot be 0mb and exceed 30mb");
            }else{
                break;
            }
        }
        addEBookToRepository(bookSize,isbn,publisher,date,author,subject,description);

    }
    public Asset addBookToRepository( String title, String urlPreview, String urlLogo, Boolean availability, String floor, String section, String row, String shelf, String ISBN, String publisher, Date date, String author, String subject, String description) {
        Book book = new Book( null,title, urlPreview, urlLogo, availability, floor, section, row, shelf, ISBN, publisher, date, author, subject, description);
        boolean checkAdd = assetRepository.addAsset(book);
        if (checkAdd) {
            System.out.println("Book Added Successfully with ID: " + book.getAssetId());
            return book;
        } else {
            System.out.println("Book addition failed or Book already exists");
            return null;
        }
    }
    public Asset addEBookToRepository( int bookSize, String isbn, String publisher, Date published, String author, String subject, String description) {
        DigitalBook dbook = new DigitalBook("www",bookSize, isbn, publisher, published, author, subject,description);
        boolean checkAdd = assetRepository.addAsset(dbook);
        if (checkAdd) {
            System.out.println("Book Added Successfully with ID: " + dbook.getAssetId());
            return dbook;
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
        boolean availability ;
        String ID;
        while(true) {
            System.out.println("Enter book ID to remove the book");
            ID = scanner.nextLine();
            availability = bookAvailibilitycheker(ID);
            if (availability){
                removeBookFromRepository(ID);
                break;
            }
        }
        System.out.println("Book removed Successfully");
    }

    public Asset removeBookFromRepository(String ID){
        return assetRepository.removeAsset(ID);
    }


    public boolean bookAvailibilitycheker(String ID){
        Asset asset;
        int count = 0;
        do{
            if (count > 0){
                System.out.println("No book found with entered book ID");
                return false;
            }
            asset = assetRepository.getAsset(ID);
            count++;
        }while(asset == null);
        return true;
    }

    public boolean checkBookBeforeAdd(Book book){


        return true;
    }
    public void updateBook() {
        displayAsstes();
        boolean availability ;
        String id;
        while(true) {
            System.out.println("Enter book ID to update the book");
            id = scanner.nextLine();
            availability = bookAvailibilitycheker(id);
            if (availability){
                break;
            }
        }
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

    public void addNewsLetter() {
        System.out.println("Enter Newsletter's publication");
        String publication = scanner.nextLine();
        System.out.println("Enter Published Date in format dd/MM/yyyy");
        String dateStr = scanner.nextLine();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid Date Format");
            return;
        }

        if (newsletterExists(publication, date)) {
            System.out.println("This month's newsletter already exists for the publication.");
        } else {

            System.out.println("Enter Newsletter's Access Link");
            String accessLink = scanner.nextLine();
            NewsLetter newNewsLetter = new NewsLetter(accessLink, date, publication);
            boolean added = assetRepository.addAsset(newNewsLetter);
            if (added) {
                System.out.println("Newsletter added successfully.");
            } else {
                System.out.println("Failed to add newsletter.");
            }
        }
    }

    private boolean newsletterExists(String publication, Date date) {
        List<Asset> allAssets = assetRepository.getAllAssets();
        Calendar calendar = Calendar.getInstance();

        return allAssets.stream()
                .filter(asset -> asset instanceof NewsLetter)
                .map(asset -> (NewsLetter) asset)
                .anyMatch(newsLetter -> {
                    calendar.setTime(newsLetter.getDate());
                    int year = calendar.get(Calendar.YEAR);
                    int month = calendar.get(Calendar.MONTH);

                    calendar.setTime(date);
                    int checkYear = calendar.get(Calendar.YEAR);
                    int checkMonth = calendar.get(Calendar.MONTH);

                    return newsLetter.getPublication().equalsIgnoreCase(publication) && year == checkYear && month == checkMonth;
                });
    }


///////////////////////////////////////////////////////


public void returnAsset() {
    User user = App.getLoggedInUser();
    List<LoanAsset> loanedItemsForUser = printAndGetBorrowingHistory(user);
    
    System.out.println("Press the asset ID to return:");
    String assetId = scanner.nextLine();
    
    boolean assetReturned = false;
    for (LoanAsset loan : loanedItemsForUser) {
        if (loan.getAssetId().equals(assetId) && loan.getActualReturnDate() == null) {
            loan.setActualReturnDate(new Date());
            loanAssetRepository.saveLoanAsset(loan);
            System.out.println("Asset returned successfully.");
            assetReturned = true;
            
            // Update asset availability
            Asset asset = assetRepository.getAsset(assetId);
            if (asset != null) {
                asset.setAvailability(true);
                asset.updateAsset();
            }
            
            break;
        }
    }
    if (!assetReturned) {
        System.out.println("Asset is not currently on loan or does not exist.");
    }
}


}