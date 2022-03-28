package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "userManager";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "userr";

    private static final String KEY_EMAIL = "email";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";

    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_students_table = String.format("CREATE TABLE %s(%s TEXT PRIMARY KEY, %s TEXT, %s TEXT)", TABLE_NAME, KEY_EMAIL, KEY_USERNAME, KEY_PASSWORD);
        db.execSQL(create_students_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_students_table = String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);
        db.execSQL(drop_students_table);

        onCreate(db);
    }

    public void insert(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EMAIL, user.getEmail());
        values.put(KEY_USERNAME, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());

        database.insert(TABLE_NAME, null, values);
        database.close();
    }

    public User getUser(String email){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, null, KEY_EMAIL+" = ?", new String[] {email}, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        User user = new User();
        user.setEmail(cursor.getString(0));
        user.setUsername(cursor.getString(1));
        user.setPassword(cursor.getString(2));
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return user;
    }

    public void updateUser(User user){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_USERNAME, user.getUsername());
        contentValues.put(KEY_EMAIL, user.getEmail());
        contentValues.put(KEY_PASSWORD, user.getPassword());

        database.update(TABLE_NAME, contentValues, KEY_EMAIL+" = ?", new String[]{user.getEmail()});
        database.close();
    }

    public void deleteUser(String email){
        SQLiteDatabase database = this.getWritableDatabase();
        database.delete(TABLE_NAME, KEY_EMAIL + " = ?", new String[]{email});
        database.close();
    }
}
