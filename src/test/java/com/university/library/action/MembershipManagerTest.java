package com.university.library.action;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.university.library.App;
import com.university.library.model.LoanAsset;
import com.university.library.model.Membership;
import com.university.library.model.assets.Asset;
import com.university.library.model.assets.physical.Book;
import com.university.library.model.assets.physical.Laptop;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.AssetRepository;
import com.university.library.repository.LoanAssetRepository;
import com.university.library.repository.UserRepository;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MembershipManagerTest {
    @Test
    public void testBuyMembership() {
        InputStream originalIn = System.in;
        PrintStream originalOut = System.out;
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        ByteArrayInputStream inContent = new ByteArrayInputStream("b\n".getBytes());
        System.setIn(inContent);
        System.setOut(new PrintStream(outContent));
        // MembershipManager membershipManager = new MembershipManager();
        Membership membership = MembershipManager.buyMembership();

        // System.out.println("print these elements:" + membershipManager);
        String expectedOutput = "The membership period is 3 months for 58CAD\n" +
                "To continue to purchase click 'b'\n" +
                "membership details\n" +
                "Start date : 2024-03-03\n" +
                "End date : 2024-06-03\n" +
                "amount paid : 58.0\n" +
                "statusOFMembership : true\n" +
                "get userid :  1\n" +
                "membership successfully purchased";

        assertEquals(expectedOutput, outContent.toString());

        // assertEquals(UserRole.FREE_USER, App.getLoggedInUser().getUserRole());
    }
}