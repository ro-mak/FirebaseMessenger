package ru.makproductions.firebasemessenger.model;

import android.databinding.ObservableArrayList;
import android.text.Editable;

public class History {
    private ObservableArrayList<String> history;

    public History() {
        history = new ObservableArrayList<>();
    }

    public void addMessage(String message) {
        history.add(message);
    }

    public void addMessage(Editable message) {
        history.add(message.toString());
    }

    public void clearHistory() {
        history.clear();
    }
}
