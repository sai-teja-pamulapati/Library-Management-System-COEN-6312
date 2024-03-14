package com.university.library.action;

import com.university.library.App;
import com.university.library.model.PresentationRoom;
import com.university.library.model.RoomBooking;
import com.university.library.model.users.User;
import com.university.library.repository.PresentationRoomRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
                    roomBookingProcess(currentLoggedInUser);
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

    private void roomBookingProcess(User currentLoggedInUser) {
        if (hasReachedBookingLimit(currentLoggedInUser)) {
            System.out.println("You cannot have more than three bookings.");
            return;
        }

        int roomId = displayAndSelectAvailableRooms();
        if (roomId == -1) {
            System.out.println("Invalid selection. Going back to main menu.");
            return;
        }

        LocalDate bookingDate = readDate("Booking Start Date (YYYY-MM-DD):");

        if (bookingDate.isBefore(LocalDate.now())) {
            System.out.println("Bookings must be made for future dates.");
            return;
        } else if (!isWithinTwoWeeksRange(bookingDate)) {
            System.out.println("Rooms cannot be booked more than two weeks in advance.");
            return;
        } else if (userHasBookingOnDate(currentLoggedInUser, bookingDate)) {
            System.out.println("You already have a booking on this date. You cannot book more than one room in a day.");
            return;
        }

        LocalTime startTime = readTime("Booking Start Time (HH:MM):");

        if (startTime.isBefore(LocalTime.now())) {
            System.out.println("Booking time must be in future.");
            return;
        }

        LocalTime endTime = readTime("Booking End Time (HH:MM):");

        LocalDateTime startDateTime = LocalDateTime.of(bookingDate, startTime);
        LocalDateTime endDateTime = LocalDateTime.of(bookingDate, endTime);

        if(endTime.isBefore(startTime)) {
            System.out.println("End time must be after the start time.");
            return;
        } else if (!isValidTimeRange(startDateTime, endDateTime)) {
            System.out.println("Invalid booking time. Rooms are only available between 10 a.m. to 10 p.m., duration must be min 30 mins and max 3 hours!");
            return;
        }

        if (checkNoOverlap(roomId, startDateTime, endDateTime)) {
            if (bookRoom(currentLoggedInUser.getUserId(), roomId, startDateTime, endDateTime)) {
                System.out.println("Room booked successfully.");
            } else {
                System.out.println("Failed to Book the room!!");
            }
        } else {
                System.out.println("Failed to book the room. Time conflict with other bookings.");
            }
        }

    private boolean isValidTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        LocalDateTime openTime = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(), 10, 0); // 10 a.m.
        LocalDateTime closeTime = LocalDateTime.of(startTime.getYear(), startTime.getMonth(), startTime.getDayOfMonth(), 22, 0);  // 10 p.m.
        Duration duration = Duration.between(startTime, endTime);
    
        return !startTime.isBefore(openTime) && !endTime.isAfter(closeTime) &&
                duration.toMinutes() >= 30 && duration.toHours() <= 3;
    }

    private boolean isWithinTwoWeeksRange(LocalDate startDate) {
        LocalDate today = LocalDate.now();
        LocalDate twoWeeksAhead = today.plusWeeks(2);
        return !startDate.isAfter(twoWeeksAhead);
    }

    private boolean hasReachedBookingLimit(User user) {
        List<RoomBooking> bookings = repository.getRoomsByUserId(user.getUserId());
        return bookings.size() >= 3;
    }

    private boolean checkNoOverlap(int roomId, LocalDateTime startTime, LocalDateTime endTime) {
        List<RoomBooking> roomBookings = repository.getRoomBookingsByRoomId(roomId);
        for (RoomBooking booking : roomBookings) {
            if (endTime.isAfter(booking.getStartTime()) && startTime.isBefore(booking.getEndTime())) {
                return false;
            }
        }
        return true;

    }

    public static boolean isSameDay(LocalDateTime date1, LocalDateTime date2) {
        LocalDate localDate1 = date1.toLocalDate();
        LocalDate localDate2 = date2.toLocalDate();
        return localDate1.isEqual(localDate2);
    }

    private boolean userHasBookingOnDate(User user, LocalDate date) {
        List<RoomBooking> userBookings = repository.getRoomsByUserId(user.getUserId());
        return userBookings.stream().anyMatch(booking -> isSameDay (booking.getStartTime(),date.atStartOfDay()));
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

    private LocalTime readTime(String message) {
        LocalTime time = null;
        while (time == null) {
            System.out.println(message);
            String input = scanner.nextLine();
            try {
                time = LocalTime.parse(input, DateTimeFormatter.ofPattern("HH:mm"));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:MM. Try again.");
            }
        }
        return time;
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

    private boolean bookRoom(String userId , int roomId , LocalDateTime startTime, LocalDateTime endTime) {
        RoomBooking room = new RoomBooking(roomId, userId, startTime, endTime);
        return repository.addRoom(room);
    }

    private void cancelBookingProcess(User currentLoggedInUser) {
        List<RoomBooking> bookings = repository.getRoomsByUserId(currentLoggedInUser.getUserId());
        
        if (bookings.isEmpty()) {
            System.out.println("You have no bookings to cancel.");
            return;
        }
        
        System.out.println("Select a booking to cancel:");
        for (int i = 0; i < bookings.size(); i++) {
            System.out.println((i + 1) + ": " + bookings.get(i));
        }
        
        int selection = scanner.nextInt();

        if (selection < 1 || selection > bookings.size()) {
            System.out.println("Invalid selection. Going back to main menu.");
            return;
        }
        
        RoomBooking bookingToCancel = bookings.get(selection - 1);
        
        if (repository.removeRoom(bookingToCancel)) {
            System.out.println("Booking cancelled successfully.");
        } else {
            System.out.println("Failed to cancel the booking.");
        }
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
