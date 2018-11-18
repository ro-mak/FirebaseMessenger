package ru.makproductions.firebasemessenger.model.user;

import android.text.Editable;

import java.util.ArrayList;

import ru.makproductions.firebasemessenger.model.History;

public class User {
    private long userId;
    private String name;
    private String surname;
    private String status;
    private boolean isOnline;
    private ArrayList<User> friendsList;
    private History history;
    private Editable message;

    public Editable getMessage() {
        return message;
    }

    public void setMessage(Editable message) {
        this.message = message;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    void markNew() {
        UserUnitOfWork.getCurrent().registerNewUser(this);
    }

    void markChanged() {
        UserUnitOfWork.getCurrent().registerChangedUser(this);
    }

    void markRemoved() {
        UserUnitOfWork.getCurrent().registerRemovedUser(this);
    }
}
