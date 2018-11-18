package ru.makproductions.firebasemessenger.model.user;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.annotation.Nullable;

public class UsersDataBaseCreator extends SQLiteOpenHelper {

    private static final String DATA_BASE_NAME = "messengerUsers.db";
    private static final int DATA_BASE_VERSION = 1;
    static final String TABLE_USERS = "users";
    static final String COLUMN_ID = BaseColumns._ID;
    static final String COLUMN_NAME = "name";
    static final String COLUMN_SURNAME = "surname";
    static final String COLUMN_STATUS = "status";


    UsersDataBaseCreator(@Nullable Context context) {
        super(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_USERS + " (" + COLUMN_ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_NAME + " TEXT," + COLUMN_SURNAME + " TEXT," + COLUMN_STATUS + " TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < newVersion) {
            //код обновления
        }
    }
}
