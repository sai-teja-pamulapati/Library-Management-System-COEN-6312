package com.university.library.repository;

import com.university.library.model.DiscussionRoom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscussionRoomRepository {
    private Map<String, DiscussionRoom> bookingsByDateAndRoom = new HashMap<>();
    private Map<String, DiscussionRoom> bookingsByStudentAndDate = new HashMap<>();

    private String createKey(int roomId, String date) {
        return roomId + "-" + date;
    }

    private String createStudentDateKey(String emailId, String date) {
        return emailId + "-" + date;
    }

    public boolean addRoom(DiscussionRoom room) {
        String roomDateKey = createKey(room.getRoomId(), room.getBookingDate());
        String studentDateKey = createStudentDateKey(room.getEmailId(), room.getBookingDate());

        if (bookingsByDateAndRoom.containsKey(roomDateKey) || bookingsByStudentAndDate.containsKey(studentDateKey)) {
            return false; // Room is already booked for this date or student has a booking on this date
        }

        bookingsByDateAndRoom.put(roomDateKey, room);
        bookingsByStudentAndDate.put(studentDateKey, room);
        return true;
    }

    public DiscussionRoom getRoomByDateAndRoomId(int roomId, String bookingDate) {
        return bookingsByDateAndRoom.get(createKey(roomId, bookingDate));
    }

    public List<DiscussionRoom> getRoomsByStudentEmail(String emailId) {
        List<DiscussionRoom> result = new ArrayList<>();
        for (String key : bookingsByStudentAndDate.keySet()) {
            if (key.startsWith(emailId + "-")) {
                result.add(bookingsByStudentAndDate.get(key));
            }
        }
        return result;
    }

    public boolean removeRoom(int roomId, String bookingDate) {
        String key = createKey(roomId, bookingDate);
        DiscussionRoom room = bookingsByDateAndRoom.remove(key);
        if (room != null) {
            bookingsByStudentAndDate.remove(createStudentDateKey(room.getEmailId(), bookingDate));
            return true;
        }
        return false;
    }
}
