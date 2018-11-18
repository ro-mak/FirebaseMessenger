package ru.makproductions.firebasemessenger.model.user;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Closeable;
import java.io.IOException;

public class UsersDataBaseReader implements Closeable {

    private static final int COLUMN_INDEX = 0;
    private static final int COLUMN_NAME = 1;
    private static final int COLUMN_SURNAME = 2;
    private static final int COLUMN_STATUS = 3;
    private Cursor cursor;
    private SQLiteDatabase database;
    private String[] weatherTableColumns = {UsersDataBaseCreator.COLUMN_ID, UsersDataBaseCreator.COLUMN_NAME, UsersDataBaseCreator.COLUMN_SURNAME, UsersDataBaseCreator.COLUMN_STATUS};

    UsersDataBaseReader(SQLiteDatabase database) {
        this.database = database;
    }

    void open() {
        sendQuery();
        cursor.moveToFirst();
    }

    private void sendQuery() {
        cursor = database.query(UsersDataBaseCreator.TABLE_USERS, weatherTableColumns, null, null, null, null, null);
    }

    User readDataInPosition(int position) {
        cursor.moveToPosition(position);
        return cursorToUser();
    }

    User cursorToUser() {
        return new UserBuilder()
                .withId(cursor.getInt(COLUMN_INDEX))
                .withName(cursor.getString(COLUMN_NAME))
                .withSurname(cursor.getString(COLUMN_SURNAME))
                .withStatus(cursor.getString(COLUMN_STATUS))
                .build();
    }


    void refreshCursor() {
        int position = cursor.getPosition();
        sendQuery();
        cursor.moveToPosition(position);
    }

    int getNumberOfItems() {
        return cursor.getCount();
    }

    @Override
    public void close() throws IOException {
        cursor.close();
    }
}
