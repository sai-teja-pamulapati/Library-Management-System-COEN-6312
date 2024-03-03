package com.university.library.repository;

import com.university.library.model.PresentationRoom;
import com.university.library.model.RoomBooking;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PresentationRoomRepository {
    private Map<String, RoomBooking> bookingsByDateAndRoom = new HashMap<>();
    private Map<String, List<RoomBooking>> bookingsByStudent = new HashMap<>();
    private Map<Integer, PresentationRoom> presentationRooms = new HashMap<>();

    public PresentationRoomRepository() {
        initializePredefinedRooms();
    }

    private void initializePredefinedRooms() {
        List<String> featuresRoom1 = new ArrayList<>();
        featuresRoom1.add("Projector");
        featuresRoom1.add("Whiteboard");
        presentationRooms.put(101, new PresentationRoom(101, featuresRoom1));
        
        List<String> featuresRoom2 = new ArrayList<>();
        featuresRoom2.add("Video Conference");
        featuresRoom2.add("Sound System");
        presentationRooms.put(102, new PresentationRoom(102, featuresRoom2));
        
        List<String> featuresRoom3 = new ArrayList<>();
        featuresRoom3.add("Projector");
        featuresRoom3.add("Table and chairs");
        presentationRooms.put(201, new PresentationRoom(201, featuresRoom3));
        
        List<String> featuresRoom4 = new ArrayList<>();
        featuresRoom4.add("Video Conference");
        featuresRoom4.add("Computers");
        presentationRooms.put(202, new PresentationRoom(202, featuresRoom4));
        
        List<String> featuresRoom5 = new ArrayList<>();
        featuresRoom5.add("Projector");
        featuresRoom5.add("Whiteboard");
        featuresRoom5.add("Charging sockets");
        presentationRooms.put(301, new PresentationRoom(301, featuresRoom5));
        
        List<String> featuresRoom6 = new ArrayList<>();
        featuresRoom6.add("Video Conference");
        featuresRoom6.add("Internet");
        featuresRoom6.add("Charging sockets");
        presentationRooms.put(302, new PresentationRoom(302, featuresRoom6));
    }

    public void addPresentationRoom(PresentationRoom room) {
        presentationRooms.put(room.getRoomId(), room);
    }

    public PresentationRoom getPresentationRoom(int roomId) {
        return presentationRooms.get(roomId);
    }

    public Map<Integer, PresentationRoom> getAllPresentationRooms() {
        return new HashMap<>(presentationRooms);
    }

    public boolean addRoom(RoomBooking room) {
        String key = createKey(room.getRoomId(), room.getStartDate());
        if (bookingsByDateAndRoom.containsKey(key)) {
            return false;
        }

        bookingsByDateAndRoom.put(key, room);
        bookingsByStudent.computeIfAbsent(room.getUserId(), k -> new ArrayList<>()).add(room);
        return true;
    }

    public boolean removeRoom(int roomId, String startDate) {
        String key = createKey(roomId, startDate);
        if (!bookingsByDateAndRoom.containsKey(key)) {
            return false;
        }

        RoomBooking removedRoom = bookingsByDateAndRoom.remove(key);
        List<RoomBooking> userBookings = bookingsByStudent.get(removedRoom.getUserId());
        userBookings.removeIf(room -> room.getRoomId() == roomId && room.getStartDate().equals(startDate));
        return true;
    }

    public List<RoomBooking> getRoomsByStudentEmail(String userId) {
        return bookingsByStudent.getOrDefault(userId, new ArrayList<>());
    }

    private String createKey(int roomId, String date) {
        return roomId + "-" + date;
    }
}
