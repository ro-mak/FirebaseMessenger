package ru.makproductions.firebasemessenger.model.user;

import android.app.Activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserUnitOfWork {

    private List<User> newUsers = new ArrayList<>();
    private List<User> changedUsers = new ArrayList<>();
    private List<User> removedUsers = new ArrayList<>();
    private Map<Long, User> userMap = new HashMap<>();
    private static UserUnitOfWork userUnitOfWork;
    private static UserMapper userMapper;

    private UserUnitOfWork() {

    }

    public static UserUnitOfWork getInstance(Activity activity) {
        if (userUnitOfWork == null) {
            userUnitOfWork = new UserUnitOfWork();
            userMapper = new UserMapper(new UsersDataBaseSaver(activity));
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

    private static ThreadLocal current = new ThreadLocal();

    public static void newCurrent() {
        setCurrent(new UserUnitOfWork());
    }

    static void setCurrent(UserUnitOfWork userUnitOfWork) {
        current.set(userUnitOfWork);
    }

    public static UserUnitOfWork getCurrent() {
        return (UserUnitOfWork) current.get();
    }


    public static void addUser(User user) {
        Map<Long, User> map = userUnitOfWork.userMap;
        if ((map.get(user.getUserId())) == null) {
            userMapper.insertUser(user);
        }
        map.put(user.getUserId(), user);
    }

    public static User getUser(int key) {
        Map<Long, User> map = userUnitOfWork.userMap;
        User user = map.get((long) key);
        if (user == null) {
            return userMapper.findUserById(key);
        } else {
            return user;
        }
    }
}
