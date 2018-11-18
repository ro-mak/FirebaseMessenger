package ru.makproductions.firebasemessenger.model.user;

public class UserMapper {
    private UsersDataBaseReader usersDataBaseReader;
    private UsersDataBaseSaver usersDataBaseSaver;

    public UserMapper(UsersDataBaseSaver usersDataBaseSaver) {
        this.usersDataBaseSaver = usersDataBaseSaver;
        this.usersDataBaseSaver.openDatabase();
        this.usersDataBaseReader = usersDataBaseSaver.getUsersDataBaseReader();
    }

    public User findUserById(int userId) {
        if (usersDataBaseReader == null)
            throw new RuntimeException("DataBaseReader == null in UserMapper");
        usersDataBaseReader.readDataInPosition(userId);
        return usersDataBaseReader.cursorToUser();
    }

    public void insertUser(User user) {
        user.setUserId(usersDataBaseSaver.saveUser(user.getName(), user.getSurname(), user.getStatus()));
    }

    public void updateUser(User user) {
        usersDataBaseSaver.changeUser(user);
    }

    public void deleteUser(User user) {
        usersDataBaseSaver.deleteUser(user);
    }
}
