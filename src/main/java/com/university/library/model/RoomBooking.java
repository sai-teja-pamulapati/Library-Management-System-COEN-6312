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
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Bookings must be made for future dates.");
        } else if(endDate != null && startDate != null && endDate.isBefore(startDate)) {
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

    // Implementation of the OCL constraint to check for overlapping bookings
    public boolean overlapsWith(RoomBooking otherBooking) {
        return this.roomId == otherBooking.roomId &&
                this.endDate.isAfter(otherBooking.startDate) &&
                this.startDate.isBefore(otherBooking.endDate);
    }
}
