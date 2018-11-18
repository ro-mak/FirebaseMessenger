package ru.makproductions.firebasemessenger.model.user;

import android.app.Activity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUnitOfWork {

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
            addUser(user);
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

    public static void addUser(User user) {
        Map<Long, User> map = userMap;
        if ((map.get(user.getUserId())) == null) {
            userMapper.insertUser(user);
        }
        map.put(user.getUserId(), user);
    }

    public static User getUser(int key) {
        if (userMapper == null) throw new RuntimeException("UserMapper null in UserUnitOfWork");
        Map<Long, User> map = userMap;
        User user = map.get((long) key);
        if (user == null) {
            return userMapper.findUserById(key);
        } else {
            return user;
        }
    }
}
