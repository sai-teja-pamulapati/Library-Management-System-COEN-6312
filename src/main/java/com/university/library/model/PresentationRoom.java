package com.university.library.model;

import java.util.List;

public class PresentationRoom {
    private int roomId;
    private List<String> features;

    public PresentationRoom(int roomId, List<String> features) {
        this.roomId = roomId;
        this.features = features;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<String> getFeatures() {
        return features;
    }

    public void setFeatures(List<String> features) {
        this.features = features;
    }
}
