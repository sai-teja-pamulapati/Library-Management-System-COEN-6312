package com.university.library.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import com.university.library.model.PresentationRoom;
import com.university.library.model.RoomBooking;
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
                    getRoomsByUserId();
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
        int roomId = displayAndSelectAvailableRooms();
        if (roomId == -1) {
            System.out.println("Invalid selection. Going back to main menu.");
            return;
        }
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

    private int displayAndSelectAvailableRooms() {
        System.out.println("Available Presentation Rooms:");
        Map<Integer, PresentationRoom> rooms = repository.getAllPresentationRooms();
        List<Integer> roomIds = new ArrayList<>(rooms.keySet());
        int index = 1;
        for (Integer roomId : roomIds) {
            PresentationRoom room = rooms.get(roomId);
            System.out.println(index++ + ". Room ID: " + room.getRoomId() + ", Features: " + String.join(", ", room.getFeatures()));
        }
        
        System.out.println("Select a room by entering its number:");
        try {
            int selection = Integer.parseInt(scanner.nextLine());
            if (selection < 1 || selection > roomIds.size()) {
                return -1; // Invalid selection
            }
            return roomIds.get(selection - 1); // Return the selected room ID
        } catch (NumberFormatException e) {
            return -1; // Handle non-integer input
        }
    }

    private boolean bookRoom(int roomId, String startDate, String endDate) {
        RoomBooking room = new RoomBooking(roomId, this.userId, startDate, endDate);
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

    private void getRoomsByUserId() {
        System.out.println("Booking history for user: " + this.userId);
        List<RoomBooking> bookings = repository.getRoomsByStudentEmail(this.userId);
        if (bookings.isEmpty()) {
            System.out.println("No booking history found.");
        } else {
            for (RoomBooking booking : bookings) {
                System.out.println("Room ID: " + booking.getRoomId() + ", Start Date: " + booking.getStartDate() + ", End Date: " + booking.getEndDate());
            }
        }
    }    
}
