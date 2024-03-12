package com.university.library.action;

import com.university.library.App;
import com.university.library.model.PresentationRoom;
import com.university.library.model.RoomBooking;
import com.university.library.model.users.User;
import com.university.library.repository.PresentationRoomRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class PresentationRoomManagement {

    private PresentationRoomRepository repository = new PresentationRoomRepository();
    private static Scanner scanner = new Scanner(System.in);

    public void manageRoomBooking() {
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose from the following options:\n" +
                "1. Book Room\n" +
                "2. Cancel Booking\n" +
                "3. Booking History\n" +
                "4. Go back");
            User currentLoggedInUser = App.getLoggedInUser();

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    bookRoomProcess(currentLoggedInUser);
                    break;
                case "2":
                    cancelBookingProcess(currentLoggedInUser);
                    break;
                case "3":
                    displayBookingHistory(currentLoggedInUser);
                    break;
                case "4":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void bookRoomProcess(User currentLoggedInUser) {

        int roomId = displayAndSelectAvailableRooms();
        if (roomId == -1) {
            System.out.println("Invalid selection. Going back to main menu.");
            return;
        }

        LocalDate startDate = readDate("Booking Start Date (YYYY-MM-DD):");
        LocalDate endDate = readDate("Booking End Date (YYYY-MM-DD):");

        if (hasReachedBookingLimit(currentLoggedInUser)) {
            System.out.println("You cannot have more than three bookings.");
            return;
        }

        /*if (!isWithinTwoWeeksRange(startDate)) {
            System.out.println("Rooms cannot be booked more than two weeks in advance.");
            return;
        }*/

        // Check for overlapping bookings
        if (checkNoOverlap(roomId, startDate, endDate)) {
            if (bookRoom(currentLoggedInUser.getUserId(), roomId, startDate, endDate)) {
                System.out.println("Room booked successfully.");
            } else {
                System.out.println("Failed to book the room. Room already booked for the selected dates.");
            }
        }

    }

    /*private boolean isWithinTwoWeeksRange(LocalDate startDate) {
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksAhead = today.plusWeeks(2);
        return !startDate.isAfter(twoWeeksAhead);
    }*/

    private boolean hasReachedBookingLimit(User user) {
        List<RoomBooking> bookings = repository.getRoomsByUserId(user.getUserId());
        return bookings.size() >= 3; // Check if the user already has three bookings
    }

    private boolean checkNoOverlap(int roomId, LocalDate startDate, LocalDate endDate) {
        List<RoomBooking> roomBookings = repository.getRoomBookingsByRoomId(roomId);
        for (RoomBooking booking : roomBookings) {
            if (endDate.isAfter(booking.getStartDate()) && startDate.isBefore(booking.getEndDate())) {
                return false; // Overlapping booking found
            }
        }
        return true; // No overlapping booking found
    }

    private LocalDate readDate(String message) {
        LocalDate date = null;
        while (date == null) {
            System.out.println(message);
            String input = scanner.nextLine();
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                date = LocalDate.parse(input, formatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please use YYYY-MM-DD. Try again.");
            }
        }
        return date;
    }

    private int displayAndSelectAvailableRooms() {
        System.out.println("Available Presentation Rooms:");
        Map<Integer, PresentationRoom> rooms = repository.getAllPresentationRooms();
        List<Integer> roomIds = new ArrayList<>(rooms.keySet());
        int index = 1;
        for (Integer roomId : roomIds) {
            PresentationRoom room = rooms.get(roomId);
            System.out.println(index++ + ". " + room);
        }
        
        System.out.println("Select a room by entering its number:");
        try {
            int selection = Integer.parseInt(scanner.nextLine());
            if (selection < 1 || selection > roomIds.size()) {
                return -1;
            }
            return roomIds.get(selection - 1);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean bookRoom(String userId , int roomId , LocalDate startDate , LocalDate endDate) {
        RoomBooking room = new RoomBooking(roomId, userId, startDate, endDate);
        return repository.addRoom(room);
    }

    private void cancelBookingProcess(User currentLoggedInUser) {
        System.out.println("Enter Room ID:");
        int roomId = Integer.parseInt(scanner.nextLine());
        LocalDate startDate = readDate("Booking Start Date (YYYY-MM-DD):");
        if (cancelBooking(currentLoggedInUser.getUserId(), roomId, startDate)) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Failed to cancel the booking.");
        }
    }

    private boolean cancelBooking(String userId , int roomId , LocalDate startDate) {
        return repository.removeRoom(userId, roomId, startDate);
    }

    private void displayBookingHistory(User currentLoggedInUser) {
        System.out.println("Booking history for user: " + currentLoggedInUser.getName());
        List<RoomBooking> bookings = repository.getRoomsByUserId(currentLoggedInUser.getUserId());
        if (bookings.isEmpty()) {
            System.out.println("No booking history found.");
            return;
        }
        for (RoomBooking booking : bookings) {
            System.out.println(booking);
        }
    }
}
