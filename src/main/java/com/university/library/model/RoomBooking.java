package com.university.library.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RoomBooking {
    private int roomId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;

    public RoomBooking(int roomId, String userId, LocalDate startDate, LocalDate endDate) {
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Room ID: " + roomId + 
               ", Start Date: " + startDate.format(formatter) + 
               ", End Date: " + endDate.format(formatter);
    }
}
