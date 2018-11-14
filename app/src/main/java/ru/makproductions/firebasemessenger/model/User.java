package ru.makproductions.firebasemessenger.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.Editable;

import java.util.ArrayList;

import ru.makproductions.firebasemessenger.BR;

public class User extends BaseObservable {
    private String name;
    private String surname;
    private String status;
    private boolean isOnline;
    private ArrayList<User> friendsList;
    private History history;
    private Editable message;

    @Bindable
    public Editable getMessage() {
        return message;
    }

    public void setMessage(Editable message) {
        this.message = message;
        notifyPropertyChanged(BR.message);
    }

    @Bindable
    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
        notifyPropertyChanged(BR.history);
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

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
        notifyPropertyChanged(BR.surname);
    }

    @Bindable
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
