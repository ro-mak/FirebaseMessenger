package ru.makproductions.firebasemessenger.model.user;

import android.arch.lifecycle.LiveData;

public interface UserRepository {
    LiveData<User> getUser(int key);

    void addUser(LiveData<User> user);
}
