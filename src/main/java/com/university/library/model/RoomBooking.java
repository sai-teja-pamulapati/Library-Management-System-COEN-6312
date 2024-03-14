package com.university.library.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RoomBooking {
    private int roomId;
    private String userId;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public RoomBooking(int roomId, String userId, LocalDateTime startTime, LocalDateTime endTime) {
        this.roomId = roomId;
        this.userId = userId;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and setters
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Room ID: " + roomId +
                ", Booking Start Time: " + startTime.format(dateFormatter) +
                ", Booking End Time: " + endTime.format(dateFormatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomBooking that = (RoomBooking) o;
        return roomId == that.roomId && userId.equals(that.userId) &&
                startTime.equals(that.startTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, userId, startTime, endTime);
    }

}
