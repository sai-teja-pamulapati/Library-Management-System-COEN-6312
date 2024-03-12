package com.university.library.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RoomBooking {
    private int roomId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;

    public RoomBooking(int roomId, String userId, LocalDate startDate, LocalDate endDate) {
        this.roomId = roomId;
        this.userId = userId;
        setStartDate(startDate);
        setEndDate(endDate);
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
        if (endDate != null && startDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after the start date");
        }
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after the start date");
        }
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return "Room ID: " + roomId +
                ", Start Date: " + startDate.format(formatter) +
                ", End Date: " + endDate.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomBooking that = (RoomBooking) o;
        return roomId == that.roomId &&
                userId.equals(that.userId) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, userId, startDate, endDate);
    }

    public static boolean overlapsWith(RoomBooking booking1, RoomBooking booking2) {
        boolean overlaps = booking1.roomId == booking2.roomId &&
        !booking1.endDate.isBefore(booking2.startDate) &&
        !booking1.startDate.isAfter(booking2.endDate);
        // debug
        System.out.println("Checking overlap between:");
        System.out.println("Booking 1: " + booking1);
        System.out.println("Booking 2: " + booking2);
        System.out.println("Overlap: " + overlaps);

        return overlaps;
    }
}

