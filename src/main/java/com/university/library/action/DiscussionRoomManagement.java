package com.university.library.action;

import com.university.library.model.DiscussionRoom;
import com.university.library.repository.DiscussionRoomRepository;

public class DiscussionRoomManagement {
    private DiscussionRoomRepository repository = new DiscussionRoomRepository();

    public boolean bookRoom(int roomId, String bookingDate, String studentEmail) {
        // Check if the room is available for booking on the given date
        DiscussionRoom room = repository.getRoomByDateAndRoomId(roomId, bookingDate);
        if (room != null) {
            System.out.println("Room is already booked for this date.");
            return false;
        }

        // Check if the student has already booked a room on this date
        room = repository.getRoomByStudentAndDate(studentEmail, bookingDate);
        if (room != null) {
            System.out.println("Student has already booked a room on this date.");
            return false;
        }

        // If the checks pass, proceed with booking
        DiscussionRoom newRoom = new DiscussionRoom(roomId, bookingDate, studentEmail);
        return repository.addRoom(newRoom);
    }

    // Method to cancel a booking
    public boolean cancelBooking(int roomId, String bookingDate, String studentEmail) {
        // Retrieve the booking based on roomId and bookingDate
        DiscussionRoom room = repository.getRoomByDateAndRoomId(roomId, bookingDate);
        if (room == null || !room.getStudentId().equals(studentEmail)) {
            System.out.println("No booking found for the given details or mismatched student email.");
            return false;
        }

        return repository.removeRoom(roomId, bookingDate);
    }

}
