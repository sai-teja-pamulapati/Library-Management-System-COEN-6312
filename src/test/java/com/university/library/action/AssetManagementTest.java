package com.university.library.action;

import com.university.library.model.LoanAsset;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.AssetRepository;
import com.university.library.repository.LoanAssetRepository;
import com.university.library.repository.UserRepository;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AssetManagementTest extends TestCase {

    @Mock
    private LoanAssetRepository loanAssetRepository;

    @Mock
    private AssetRepository assetRepository;

    @InjectMocks
    private AssetManagement assetManagement;

    @Mock
    private UserRepository userRepository;

    private User testUser;

    private List<LoanAsset> testLoanAssetList;

    @Before
    public void setUp() {
        testUser = new User("Test User", "testuser@example.com", "password", "1234567890", "Test Address", "01-01-1990", "Male", UserRole.STUDENT);
        testUser.setUserId("1");
        testLoanAssetList = getLoanAssetList(testUser);
//        assetRepository = AssetRepository.getInstance();
//        loanAssetRepository = LoanAssetRepository.getInstance();
//        User testUser = new User("Test User", "testuser@example.com", "password", "1234567890", "Test Address", "01-01-1990", "Male", UserRole.STUDENT);
//        userRepository.addUser(testUser);
    }

    private List<LoanAsset> getLoanAssetList(User testUser) {
        LoanAsset loanAsset = new LoanAsset();
        loanAsset.setUserId(testUser.getUserId());
        loanAsset.setAssetId("1");
        Date today = new Date();
        loanAsset.setLoanDate(today);
        return Arrays.asList(loanAsset);
    }

    @After
    public void tearDown() {
//        userRepository.clearUsers();
    }


    public void testBrowse() {
    }

    public void testPrintAndGetBorrowingHistory() {


    }

    public void testAddBook() {
    }

    public void testTestPrintAndGetBorrowingHistory() {

        Mockito.when(loanAssetRepository.getLoanedItemsForUser(testUser.getUserId())).thenReturn(testLoanAssetList);

        List<LoanAsset> resultLoanAssetList = assetManagement.printAndGetBorrowingHistory(testUser);
        assertEquals(testLoanAssetList.size(),resultLoanAssetList.size());

    }
}