package com.university.library.action;

import java.util.Scanner;
import com.university.library.model.PresentationRoom;
import com.university.library.repository.PresentationRoomRepository;

public class PresentationRoomManagement {

    private PresentationRoomRepository repository = new PresentationRoomRepository();
    private static Scanner scanner = new Scanner(System.in);
    private String userId;

    public void setUserId(String userId) {
        this.userId = userId;
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
                    bookRoomProcess();
                    break;
                case "2":
                    cancelBookingProcess();
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

    private void bookRoomProcess() {
        System.out.println("Enter Room ID:");
        int roomId = Integer.parseInt(scanner.nextLine());
        System.out.println("Booking Start Date (YYYY-MM-DD):");
        String startDate = scanner.nextLine();
        System.out.println("Booking End Date (YYYY-MM-DD):");
        String endDate = scanner.nextLine();
        
        if (bookRoom(roomId, startDate, endDate)) {
            System.out.println("Room booked successfully.");
        } else {
            System.out.println("Failed to book the room.");
        }
    }

    private boolean bookRoom(int roomId, String startDate, String endDate) {
        PresentationRoom room = new PresentationRoom(roomId, this.userId, startDate, endDate);
        return repository.addRoom(room);
    }

    private void cancelBookingProcess() {
        System.out.println("Enter Room ID:");
        int roomId = Integer.parseInt(scanner.nextLine());
        System.out.println("Booking Start Date (YYYY-MM-DD):");
        String startDate = scanner.nextLine();
        
        if (cancelBooking(roomId, startDate)) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Failed to cancel the booking.");
        }
    }

    private boolean cancelBooking(int roomId, String startDate) {
        return repository.removeRoom(roomId, startDate);
    }

    private void displayBookingHistory() {
        System.out.println("Booking history:");
        for (PresentationRoom room : repository.getRoomsByStudentEmail(this.userId)) {
            System.out.println("Room ID: " + room.getRoomId() + ", Start Date: " + room.getStartDate() + ", End Date: " + room.getEndDate());
        }
    }
}
