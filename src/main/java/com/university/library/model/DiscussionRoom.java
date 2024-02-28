package com.university.library.model;

public class DiscussionRoom {
    private int roomId;
    private String bookingDate;
    private String studentId; // Email of the student

    public DiscussionRoom(int roomId, String bookingDate, String studentId) {
        this.roomId = roomId;
        this.bookingDate = bookingDate;
        this.studentId = studentId;
    }

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
