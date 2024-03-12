package com.university.library.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class RoomBooking {
    private int roomId;
    private String userId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTime;
    private LocalTime endTime;

    public RoomBooking(int roomId, String userId, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
        this.roomId = roomId;
        this.userId = userId;
        setStartDate(startDate);
        setEndDate(endDate);
        this.startTime = startTime;
        this.endTime = endTime;
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

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date must be after the start date");
        }
        this.endDate = endDate;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        if (startDate != null && endDate != null && endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End time must be after the start time");
        }
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return "Room ID: " + roomId +
                ", Start Date: " + startDate.format(dateFormatter) +
                ", Start Time: " + startTime.format(timeFormatter) +
                ", End Date: " + endDate.format(dateFormatter) +
                ", End Time: " + endTime.format(timeFormatter);
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
