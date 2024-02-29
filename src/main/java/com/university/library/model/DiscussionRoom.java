package com.university.library.model;

public class DiscussionRoom {
    private int roomId;
    private String bookingDate;
    private String emailId;

    public DiscussionRoom(int roomId, String bookingDate, String emailId) {
        this.roomId = roomId;
        this.bookingDate = bookingDate;
        this.emailId = emailId;
    }

   
    public int getRoomId() {
        return roomId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}
