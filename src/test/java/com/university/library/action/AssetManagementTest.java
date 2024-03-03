package com.university.library.action;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.university.library.App;
import com.university.library.model.LoanAsset;
import com.university.library.model.assets.Asset;
import com.university.library.model.assets.physical.Book;
import com.university.library.model.assets.physical.Laptop;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.AssetRepository;
import com.university.library.repository.LoanAssetRepository;
import com.university.library.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AssetManagementTest {

    @Mock
    LoanAssetRepository loanAssetRepository;

    @Mock
    AssetRepository assetRepository;

    @InjectMocks
    AssetManagement assetManagement;

    @Mock
    UserRepository userRepository;

    private User testUser;
    private List<LoanAsset> testLoanAssetList;
    private Asset asset;
    private List<Asset> allAssets;

    private ByteArrayOutputStream outContent;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        allAssets = getAllAssets();

        testUser = new User("Test User", "testuser@example.com", "password", "1234567890", "Test Address", "01-01-1990", "Male", UserRole.STUDENT);
        testUser.setUserId("1");
        asset = new Book("12" ,"title", "URLpreview", "URLlogo", true, "floor", "section", "row", "shelf", "ISBN", "publisher", new Date(), "author", "subject", "description");

        testLoanAssetList = getLoanAssetList(testUser, asset);
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }


    private List<Asset> getAllAssets() {
        Gson gson = new Gson();

        List<Asset> allAssets = new ArrayList<>();
        // Use this Gson instance for JSON parsing
        try {
            JsonReader reader = new JsonReader(new FileReader("assets.json"));
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonObject jsonObject = jsonElement.getAsJsonObject();

            int assetId = 1;
            JsonArray books = jsonObject.getAsJsonArray("books");
            for (JsonElement book : books) {
                Asset book1 = gson.fromJson(book, Book.class);
                book1.setAssetId(String.valueOf(assetId++));
                allAssets.add(book1);
            }

            JsonArray laptops = jsonObject.getAsJsonArray("laptops");
            for (JsonElement laptop : laptops) {
                Asset laptop1 = gson.fromJson(laptop, Laptop.class);
                laptop1.setAssetId(String.valueOf(assetId++));
                allAssets.add(laptop1);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return allAssets;
    }

    private List<LoanAsset> getLoanAssetList(User testUser , Asset asset) {
        LoanAsset loanAsset = new LoanAsset();
        loanAsset.setUserId(testUser.getUserId());
        loanAsset.setAssetId(asset.getAssetId());
        Date today = new Date();
        loanAsset.setLoanDate(today);
        return Arrays.asList(loanAsset);
    }

    PrintStream originalOut = System.out;

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
        System.setIn(System.in);
    }

    @Test
    public void testBrowse() {
    }

    @Test
    public void testAddBook() {
        // input for book details
        String simulatedInput = String.join("\n",
                "ABCD", // Title
                "http://www.abcdpreview.com", // URL preview
                "http://www.abcdlogo.com", // URL logo
                "0123456789", // ISBN
                "ABCD Private", // Publisher
                "01/01/2020", // Published Date
                "XYZ", // Author
                "Alphabet", // Subject
                "Description", // Description
                "2", // Floor
                "3", // Row
                "Literature", // Section
                "4" // Shelf
        );
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
        when(assetRepository.addAsset(any(Book.class))).thenReturn(true);

        assetManagement.addBook();

        verify(assetRepository).addAsset(argThat(book -> book instanceof Book &&
            "ABCD".equals(book.getTitle()) &&
            "0123456789".equals(((Book) book).getIsbn())));

        assertTrue(outContent.toString().contains("Book Added Successfully"), "Expected success message not found in console output.");
    }

@Test
public void testAddBookFailure() {
    // input for book details
    String simulatedInput = String.join("\n",
            "ABCD", // Title
            "http://www.abcdpreview.com", // URL preview
            "http://www.abcdlogo.com", // URL logo
            "0123456789", // ISBN
            "ABCD Private", // Publisher
            "01/01/2020", // Published Date
            "XYZ", // Author
            "Alphabet", // Subject
            "Description", // Description
            "2", // Floor
            "3", // Row
            "Literature", // Section
            "4"  // Shelf
    );
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    when(assetRepository.addAsset(any(Book.class))).thenReturn(false);

    assetManagement.addBook();


    assertTrue(outContent.toString().contains("Book addition failed"), "Expected failure message not found in console output.");
}

@Test
    void testRemoveBook() {
    }

    @Test
    void testPrintAndGetBorrowingHistory() {

        String assetId = testLoanAssetList.get(0).getAssetId();
        String userId = testUser.getUserId();

        when(loanAssetRepository.getLoanedItemsForUser(userId)).thenReturn(testLoanAssetList);
        when(assetRepository.getAsset(assetId)).thenReturn(asset);

        // Calling the method under test
        List<LoanAsset> resultLoanAssetList = assetManagement.printAndGetBorrowingHistory(testUser);

        // Verifying the returned list
        assertEquals(testLoanAssetList, resultLoanAssetList);

        // Verifying interactions with mock objects
        verify(loanAssetRepository, times(1)).getLoanedItemsForUser(userId);
        verify(assetRepository, times(1)).getAsset(assetId);
    }

    @Test
    public void testGetResultsForISBN() {
        String isbnTest = "9781593279509";
        String titleTest = "Eloquent JavaScript, Third Edition";
        String author = "Marijn Haverbeke";

        when(assetRepository.getAllAssets()).thenReturn(allAssets);

        // Calling the method under test
        List<Asset> resultLoanAssetList = assetManagement.getResultsForISBN(isbnTest);
        assertFalse(resultLoanAssetList.isEmpty());
        Asset resultAsset = resultLoanAssetList.get(0);
        assertTrue (resultAsset instanceof Book);
        Book resultBook = (Book) resultAsset;
        assertEquals(isbnTest, resultBook.getIsbn());
        assertEquals(titleTest, resultBook.getTitle());
        assertEquals(author, resultBook.getAuthor());
    }

    @Test
    public void testGetResultsForKeywords() {
        String isbn = "9781491943533";
        String keyword = "Bevacqua";
        String titleTest = "Practical Modern JavaScript";

        when(assetRepository.getAllAssets()).thenReturn(allAssets);

        // Calling the method under test
        List<Asset> resultLoanAssetList = assetManagement.getResultsForKeywords(keyword);
        assertFalse(resultLoanAssetList.isEmpty());
        Asset resultAsset = resultLoanAssetList.get(0);
        assertTrue (resultAsset instanceof Book);
        Book resultBook = (Book) resultAsset;
        assertTrue(resultBook.toString().contains(keyword));
        assertEquals(isbn, resultBook.getIsbn());
        assertEquals(titleTest, resultBook.getTitle());
    }

    @Test
    public void testGetResultsForAuthor() {
        String isbn = "9781449365035";
        String author = "Axel Rauschmayer";
        String titleTest = "Speaking JavaScript";

        when(assetRepository.getAllAssets()).thenReturn(allAssets);

        // Calling the method under test
        List<Asset> resultLoanAssetList = assetManagement.getResultsForAuthor(author);
        assertFalse(resultLoanAssetList.isEmpty());
        Asset resultAsset = resultLoanAssetList.get(0);
        assertTrue (resultAsset instanceof Book);
        Book resultBook = (Book) resultAsset;
        assertEquals(resultBook.getAuthor() , author);
        assertEquals(isbn, resultBook.getIsbn());
        assertEquals(titleTest, resultBook.getTitle());
    }

    @Test
    public void testGetResultsForTitle() {
        String isbn = "9781484200766";
        String author = "Scott Chacon and Ben Straub";
        String titleTest = "Pro Git";

        when(assetRepository.getAllAssets()).thenReturn(allAssets);

        // Calling the method under test
        List<Asset> resultLoanAssetList = assetManagement.getResultsForTitle(titleTest);
        assertFalse(resultLoanAssetList.isEmpty());
        Asset resultAsset = resultLoanAssetList.get(0);
        assertTrue (resultAsset instanceof Book);
        Book resultBook = (Book) resultAsset;
        assertEquals(resultBook.getAuthor() , author);
        assertEquals(isbn, resultBook.getIsbn());
        assertEquals(titleTest, resultBook.getTitle());
    }

    @Test
    public void testGetResultsForSearch() {
        String search = "Galaxy Book";
        String title = "Samsung Galaxy Book Pro";
        String brand = "Samsung";
        String model = "Galaxy Book Pro";
        String processor = "Intel Core i7-1165G7";
        when(assetRepository.getAllAssets()).thenReturn(allAssets);

        // Calling the method under test
        List<Asset> resultLoanAssetList = assetManagement.getResultsForSearch(search);
        assertFalse(resultLoanAssetList.isEmpty());
        Asset resultAsset = resultLoanAssetList.get(0);
        assertTrue (resultAsset instanceof Laptop);
        Laptop laptopResult= (Laptop) resultAsset;
        assertEquals(title, laptopResult.getTitle());
        assertEquals(brand, laptopResult.getBrand());
        assertEquals(model, laptopResult.getModel());
        assertEquals(processor, laptopResult.getProcessor());
    }

    @Test
    public void testProcessCheckout() {
        String assetId = "1";
        App.setLoggedInUser(testUser);
        LoanAsset loanAsset = assetManagement.processCheckout(allAssets , assetId);
        assertEquals(assetId , loanAsset.getAssetId());
        assertEquals(testUser.getUserId(), loanAsset.getUserId());
    }
}