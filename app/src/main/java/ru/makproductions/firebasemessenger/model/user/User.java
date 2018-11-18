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
        markChanged();
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
        markChanged();
    }

    public int numberOfFriends() {
        return friendsList.size();
    }

    public User() {
        friendsList = new ArrayList<>();
        markNew();
    }

    public void addFriend(User friend) {
        friendsList.add(friend);
        markChanged();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
        markChanged();
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        markChanged();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        markChanged();
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
        markChanged();
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
        markChanged();
    }

    void markNew() {
        UserUnitOfWork.getInstance().registerNewUser(this);
    }

    void markChanged() {
        UserUnitOfWork.getInstance().registerChangedUser(this);
    }

    void markRemoved() {
        UserUnitOfWork.getInstance().registerRemovedUser(this);
    }
}
