
    /* Implementation of the OCL constraint to check for overlapping bookings
    public boolean overlapsWith(RoomBooking otherBooking) {
        return this.roomId == otherBooking.roomId &&
                this.endDate.isAfter(otherBooking.startDate) &&
                this.startDate.isBefore(otherBooking.endDate);
    }*/
package com.university.library.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RoomBooking {
    private int roomId;
    private String userId;
    private LocalDate startDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public RoomBooking(int roomId, String userId, LocalDate startDate, LocalTime startTime, LocalTime endTime) {
        this.roomId = roomId;
        this.userId = userId;
        setStartDate(startDate);
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

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Bookings must be made for future dates.");
        }
        this.startDate = startDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        if (startTime.isBefore(LocalTime.now())) {
            throw new IllegalArgumentException("Booking time must be in future.");
        } else if(endTime != null && startTime != null && endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after the start time");
        }
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        if (startTime != null && endTime != null && endTime.isBefore(startTime)) {
            throw new IllegalArgumentException("End time must be after the start time");
        }
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "Room ID: " + roomId +
                ", Date: " + startDate.format(dateFormatter) +
                ", Start Time: " + startTime.format(timeFormatter) +
                ", End Time: " + endTime.format(timeFormatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomBooking that = (RoomBooking) o;
        return roomId == that.roomId && userId.equals(that.userId) &&
                startDate.equals(that.startDate) && startTime.equals(that.startTime) &&
                endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, userId, startDate, startTime, endTime);
    }
}
