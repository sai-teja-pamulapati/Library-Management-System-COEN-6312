package com.university.library.model.users.academic;

import java.util.Scanner;
import java.util.HashMap;


public class Librarian extends Academic{

    HashMap <String, Integer> map = new HashMap<>();

    private static Scanner scanner = new Scanner(System.in);

    private void AddBook(){
        System.out.println("Enter the Book Title to add a book");
        String title = scanner.nextLine();
        map.put(title, 1);// swapped (1, title) position due to compiler error as the hashmap in line 11 is specified with key string and value interger.
        System.out.println("Book " + title + "added successfully");
    }

    private void RemoveBook(){
        System.out.println("Enter the Book Title to Remove a book");
        String title = scanner.nextLine();
        map.remove(1);// is it supposed to be title in the place of 1?

    }

    private void UpdateBookDetails(){
        System.out.println("Enter the Book Title to update a book details");
        String title = scanner.nextLine();
    }

    private void UpdateNewsLetter(){

    }

    private void ViewLibraryActivities(){

    }

}
