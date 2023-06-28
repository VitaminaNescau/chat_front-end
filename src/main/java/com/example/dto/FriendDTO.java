package com.example.dto;

public class FriendDTO {
    private enum Status{ON,OFF};
    private String username;
    private Status status;
    
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
