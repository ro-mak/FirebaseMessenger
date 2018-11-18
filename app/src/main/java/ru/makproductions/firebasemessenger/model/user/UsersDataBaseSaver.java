package ru.makproductions.firebasemessenger.model.user;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.Closeable;
import java.io.IOException;

class UsersDataBaseSaver implements Closeable {
    private static final String TAG = "UsersDataBaseSaver";
    private UsersDataBaseCreator usersDataBaseCreator;
    private SQLiteDatabase database;
    private UsersDataBaseReader usersDataBaseReader;

    UsersDataBaseSaver(Context context) {
        usersDataBaseCreator = new UsersDataBaseCreator(context);
    }

    void openDatabase() {
        try {
            database = usersDataBaseCreator.getWritableDatabase();
            usersDataBaseReader = new UsersDataBaseReader(database);
            usersDataBaseReader.open();
        } catch (SQLException e) {
            Log.e(TAG, "openDatabase: " + e.getMessage());
        }
    }

    long saveUser(String name, String surname, String status) {
        ContentValues weatherCityValues = new ContentValues();
        weatherCityValues.put(UsersDataBaseCreator.COLUMN_NAME, name);
        weatherCityValues.put(UsersDataBaseCreator.COLUMN_SURNAME, surname);
        weatherCityValues.put(UsersDataBaseCreator.COLUMN_STATUS, status);
        long insertId = database.insert(UsersDataBaseCreator.TABLE_USERS, null, weatherCityValues);
        return insertId;
    }

    void changeUser(User user) {
        if (user == null) throw new RuntimeException("Changing User that is NULL");
        long id = user.getUserId();
        ContentValues userValues = new ContentValues();
        userValues.put(UsersDataBaseCreator.COLUMN_ID, id);
        userValues.put(UsersDataBaseCreator.COLUMN_NAME, user.getName());
        userValues.put(UsersDataBaseCreator.COLUMN_SURNAME, user.getSurname());
        userValues.put(UsersDataBaseCreator.COLUMN_STATUS, user.getStatus());
        database.update(UsersDataBaseCreator.TABLE_USERS, userValues, UsersDataBaseCreator.COLUMN_ID + "=" + id, null);
    }

    void deleteUser(User user) {
        database.delete(UsersDataBaseCreator.TABLE_USERS, UsersDataBaseCreator.COLUMN_ID + "=" + user.getUserId(), null);
    }

    void clearTable() {
        database.delete(UsersDataBaseCreator.TABLE_USERS, null, null);
    }

    @Override
    public void close() throws IOException {
        usersDataBaseCreator.close();
        usersDataBaseReader.close();
    }

    UsersDataBaseReader getUsersDataBaseReader() {
        return usersDataBaseReader;
    }
}
