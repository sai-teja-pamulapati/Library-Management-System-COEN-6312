package com.university.library;



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.university.library.action.UserLogin;
import com.university.library.action.UserRegistration;
import com.university.library.model.assets.Asset;
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

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static AssetRepository assetRepository = AssetRepository.getInstance();

    private static User loggedInUser;

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }

    public static void main(String[] args) {
        initializeSystem();
        while (true) {
            try {
                executeWorkFlow();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void initializeSystem() {
        // Hardcoded sample users

        new Admin(null,"Admin", "admin@gmail.com", "admin123", "1234567890", "123 Main St", "1990-01-01", "Male",
                "42323", "2020-02-02", "1204 Blvd Maisonneuve", "Mon-Fri 9-5", "Permanent").addUser(false);
        new Staff(null,"John Smith", "john.smith@gmail.com", "john123", "0987654321", "456 Elm St", "1992-02-02", "Female",
                "42393", "2020-03-02", "Computer Science").addUser(false);
        new Librarian("Sam Wilson", "sam.wilson@gmail.com", "sam123", "1122334455", "789 Pine St", "1993-03-03", "Other", "Female",
                "42323", "2020-02-02").addUser(false);
        new Student(null,"Sai Teja", "1", "1", "1122334455", "789 Pine St", "1993-03-03", "Other", "42323", "2020-02-02"
        , "Communication")
                .addUser(false);
        new PaidUser(null, "ram", "ram@gmail.com", "ram@123", "9632574125", "852 marc St", "0125-12-12", "Other", "STM"
        ).addUser(false);
        new FreeUser(null, "gy", "3", "3", "789456233", "753 mathie St", "8963-12-12", "Male", "Transportation"
        ).addUser(false);



        // Hardcoded Assets
        addAssets();
    }

    private static void executeWorkFlow() {
        System.out.print("Welcome to Library Management System \n" +
                "Choose from the following options. \n" +
                "1. User Login \n" +
                "2. User Registration \n" +
                "3. Exit \n");
        String firstCommand = scanner.nextLine();
        switch (firstCommand) {
            case "1":
                UserLogin.login();
                break;
            case "2":
                UserRegistration.register(true);
                break;
            case "3":
                System.out.println("Bye");
                System.exit(0);
            default:
                throw new IllegalArgumentException("Invalid option!");
        }
    }

    
    private static void addAssets() {
    Gson gson = new Gson();

    try (JsonReader reader = new JsonReader(new FileReader("assets.json"))) {
        JsonElement jsonElement = JsonParser.parseReader(reader);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        addAssetsOfType(jsonObject.getAsJsonArray("books"), Book.class, gson);
        addAssetsOfType(jsonObject.getAsJsonArray("laptops"), Laptop.class, gson);
        addAssetsOfType(jsonObject.getAsJsonArray("newsletters"), NewsLetter.class, gson);
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

private static void addAssetsOfType(JsonArray jsonArray, Class<? extends Asset> assetType, Gson gson) {
    if (jsonArray != null) {
        for (JsonElement element : jsonArray) {
            Asset asset = gson.fromJson(element, assetType);
            assetRepository.addAsset(asset);
        }
    }
}



}