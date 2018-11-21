package ru.makproductions.firebasemessenger.model.user;

import android.app.Activity;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUnitOfWork implements UserRepository {

    private static List<User> newUsers = new ArrayList<>();
    private static List<User> changedUsers = new ArrayList<>();
    private static List<User> removedUsers = new ArrayList<>();
    private static Map<Long, User> userMap = new HashMap<>();
    private static UserUnitOfWork userUnitOfWork;
    private static UserMapper userMapper;
    private static WeakReference<Activity> activity;

    private UserUnitOfWork() {

    }

    public static void init(Activity activity) {
        UserUnitOfWork.activity = new WeakReference<>(activity);
        userMapper = new UserMapper(new UsersDataBaseSaver(UserUnitOfWork.activity.get()));
    }

    public static UserUnitOfWork getInstance() {
        if (activity == null) throw new RuntimeException("UserUnitOfWork activity is not set");
        if (userUnitOfWork == null) {
            userUnitOfWork = new UserUnitOfWork();
        }
        return userUnitOfWork;
    }

    void registerNewUser(User user) {
        newUsers.add(user);
    }

    void registerChangedUser(User user) {
        changedUsers.add(user);
    }

    void registerRemovedUser(User user) {
        removedUsers.add(user);
    }

    private void insertNewUsers() {
        for (User user : newUsers) {
            MutableLiveData<User> userMutableLiveData = new MutableLiveData<>();
            userMutableLiveData.setValue(user);
            addUser(userMutableLiveData);
        }
    }

    private void updateChangedUsers() {
        for (User user : changedUsers) {
            userMapper.updateUser(user);
        }
    }

    private void deleteRemovedUsers() {
        for (User user : removedUsers) {
            userMapper.deleteUser(user);
        }
    }

    public void commit() {
        insertNewUsers();
        updateChangedUsers();
        deleteRemovedUsers();
    }

    public void addUser(LiveData<User> userLiveData) {
        User user = userLiveData.getValue();
        if (user == null) throw new RuntimeException("Trying to add null user");
        Map<Long, User> map = userMap;
        if ((map.get(user.getUserId())) == null) {
            userMapper.insertUser(user);
        }
        map.put(user.getUserId(), user);
    }

    public LiveData<User> getUser(int key) {
        final MutableLiveData<User> mutableLiveData = new MutableLiveData<>();
        if (userMapper == null) throw new RuntimeException("UserMapper null in UserUnitOfWork");
        Map<Long, User> map = userMap;
        User user = map.get((long) key);
        if (user == null) {
            mutableLiveData.setValue(userMapper.findUserById(key));
        } else {
            mutableLiveData.setValue(user);
        }
        return mutableLiveData;
    }
}
