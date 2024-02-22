package com.university.library;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.university.library.action.UserLogin;
import com.university.library.action.UserRegistration;
import com.university.library.model.assets.Asset;
import com.university.library.model.assets.physical.Book;
import com.university.library.model.users.User;
import com.university.library.model.users.UserRole;
import com.university.library.repository.AssetRepository;
import com.university.library.repository.UserRepository;

public class App {

    private static Scanner scanner = new Scanner(System.in);
    private static UserRepository userRepository = UserRepository.getInstance();
    private static AssetRepository assetRepository = AssetRepository.getInstance();


    public static void main(String[] args) {
        while (true) {
            try {
                initializeSystem();
                executeWorkFlow();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    private static void initializeSystem() {
        // Hardcoded sample users
        User user = new User("Admin" , "admin@gmail.com" , "admin123" , "1234567890" , "123 Main St" , "1990-01-01" , "Male" , UserRole.ADMIN);
        User user1 = new User("John Smith" , "john.smith@gmail.com" , "john123" , "0987654321" , "456 Elm St" , "1992-02-02" , "Female" , UserRole.STAFF);
        User user2 = new User("Sam Wilson" , "sam.wilson@gmail.com" , "sam123" , "1122334455" , "789 Pine St" , "1993-03-03" , "Other" , UserRole.LIBRARIAN);
        user.addUser();
        user1.addUser();
        user2.addUser();

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
                UserRegistration.register();
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
        try {
            JsonReader reader = new JsonReader(new FileReader("books.json"));
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            JsonArray books = jsonObject.get("books").getAsJsonArray();
            for (JsonElement book : books) {
                Asset book1 = gson.fromJson(book , Book.class);
                assetRepository.addAsset(book1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}