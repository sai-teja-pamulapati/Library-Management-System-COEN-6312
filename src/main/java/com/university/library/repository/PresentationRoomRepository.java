package com.university.library.repository;

import com.university.library.model.PresentationRoom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PresentationRoomRepository {
    private Map<String, PresentationRoom> bookingsByDateAndRoom = new HashMap<>();
    private Map<String, List<PresentationRoom>> bookingsByStudent = new HashMap<>();

    private String createKey(int roomId, String date) {
        return roomId + "-" + date;
    }

    public boolean addRoom(PresentationRoom room) {
        String key = createKey(room.getRoomId(), room.getStartDate());
        if (bookingsByDateAndRoom.containsKey(key)) {
            return false; // Room is already booked for this date
        }

        bookingsByDateAndRoom.put(key, room);
        
        bookingsByStudent.computeIfAbsent(room.getUserId(), k -> new ArrayList<>()).add(room);
        
        return true;
    }

    public boolean removeRoom(int roomId, String startDate) {
        String key = createKey(roomId, startDate);
        if (!bookingsByDateAndRoom.containsKey(key)) {
            return false; // No such booking exists
        }

        PresentationRoom removedRoom = bookingsByDateAndRoom.remove(key);
        List<PresentationRoom> userBookings = bookingsByStudent.get(removedRoom.getUserId());
        userBookings.removeIf(room -> room.getRoomId() == roomId && room.getStartDate().equals(startDate));

        return true;
    }

    public List<PresentationRoom> getRoomsByStudentEmail(String userId) {
        return bookingsByStudent.getOrDefault(userId, new ArrayList<>());
    }
}
