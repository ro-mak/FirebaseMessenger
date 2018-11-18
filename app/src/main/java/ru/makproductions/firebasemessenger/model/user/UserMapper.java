package ru.makproductions.firebasemessenger.model.user;

public class UserMapper {
    private UsersDataBaseReader usersDataBaseReader;
    private UsersDataBaseSaver usersDataBaseSaver;

    public UserMapper(UsersDataBaseSaver usersDataBaseSaver){
        this.usersDataBaseSaver = usersDataBaseSaver;
        this.usersDataBaseReader = usersDataBaseSaver.getUsersDataBaseReader();
        this.usersDataBaseSaver.openDatabase();
    }

    public User findUserById(int userId){
        usersDataBaseReader.readDataInPosition(userId);
        return usersDataBaseReader.cursorToUser();
    }

    public void insertUser (User user){
        user.setUserId(usersDataBaseSaver.saveUser(user.getName(),user.getSurname(),user.getStatus()));
    }

    public void updateUser (User user){
        usersDataBaseSaver.changeUser(user);
    }

    public void deleteUser(User user){
        usersDataBaseSaver.deleteUser(user);
    }
}
