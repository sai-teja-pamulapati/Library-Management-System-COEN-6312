package com.university.library.model;

public class PresentationRoom {
    private int roomId;
    private String userId;
    private String startDate;
    private String endDate;

    public PresentationRoom(int roomId, String userId, String startDate, String endDate) {
        this.roomId = roomId;
        this.userId = userId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

   
    public int getRoomId() {
        return roomId;
    }

    public String getUserId() {
        return userId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
