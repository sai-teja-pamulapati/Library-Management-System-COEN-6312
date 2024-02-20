import Java.util.Scanner;
import Java.util.HashMap;

public class LIBRARIAN extends ACADEMIC{

    HashMap <String, Integer> map = new HashMap<>();

    Private Static Scanner scanner = new Scanner(System.in);

    private Void AddBook(){
        System.out.println("Enter the Book Title to add a book");
        String title = scanner.nextLine();
        map.put(1, title);
        System.out.prinln("Book " + title + "added successfully");
    }

    private Void RemoveBook(){
        System.out.println("Enter the Book Title to Remove a book");
        String title = scanner.nextLine();
        map.remove(1);

    }

    private Void UpdateBookDetails(){
        System.out.println("Enter the Book Title to update a book details");
        String title = scanner.nextLine();
    }

    private Void UpdateNewsLetter(){

    }

    Private Void ViewLibraryActivities(){

    }

}
