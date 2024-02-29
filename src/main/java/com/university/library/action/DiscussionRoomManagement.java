package com.university.library.action;

import java.util.Scanner;
import com.university.library.model.DiscussionRoom;
import com.university.library.repository.DiscussionRoomRepository;

public class DiscussionRoomManagement {

    private DiscussionRoomRepository repository = new DiscussionRoomRepository();
    private static Scanner scanner = new Scanner(System.in);
    private String studentEmail;

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public void manageRoomBooking() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose from the following options:\n" +
                "1. Book Room\n" +
                "2. Cancel Booking\n" +
                "3. Booking History\n" +
                "4. Go back");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    System.out.println("Enter Room ID:");
                    int roomId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Booking Date (YYYY-MM-DD):");
                    String bookingDate = scanner.nextLine();
                    if (bookRoom(roomId, bookingDate)) {
                        System.out.println("Room booked successfully.");
                    } else {
                        System.out.println("Failed to book the room.");
                    }
                    break;
                case "2":
                    System.out.println("Enter Room ID:");
                    roomId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Booking Date (YYYY-MM-DD):");
                    bookingDate = scanner.nextLine();
                    if (cancelBooking(roomId, bookingDate)) {
                        System.out.println("Booking cancelled successfully.");
                    } else {
                        System.out.println("Failed to cancel the booking.");
                    }
                    break;
                case "3":
                    displayBookingHistory();
                    break;
                case "4":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private boolean bookRoom(int roomId, String bookingDate) {
        DiscussionRoom room = new DiscussionRoom(roomId, bookingDate, this.studentEmail);
        return repository.addRoom(room);
    }

    private boolean cancelBooking(int roomId, String bookingDate) {
        return repository.removeRoom(roomId, bookingDate);
    }

    private void displayBookingHistory() {
        System.out.println("Booking history:");
        for (DiscussionRoom room : repository.getRoomsByStudentEmail(this.studentEmail)) {
            System.out.println("Room ID: " + room.getRoomId() + ", Date: " + room.getBookingDate());
        }
    }
}
