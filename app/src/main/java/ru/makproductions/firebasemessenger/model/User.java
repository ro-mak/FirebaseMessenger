package ru.makproductions.firebasemessenger.model;

import java.util.ArrayList;

public class User {
    private String name;
    private String surname;
    private String status;
    private boolean isOnline;
    private ArrayList<User> friendsList;

    public int numberOfFriends() {
        return friendsList.size();
    }

    public User() {
        friendsList = new ArrayList<>();
    }

    public void addFriend(User friend) {
        friendsList.add(friend);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }
}
