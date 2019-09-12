package com.student.practiceexam.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "school.db";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String UserTable = "CREATE TABLE "+UserMaster.Users.TABLE_NAME+" ("+UserMaster.Users._ID+" INTEGER PRIMARY KEY,"+UserMaster.Users.COLUMN_NAME+" TEXT,"
                +UserMaster.Users.COLUMN_PASSWORD+" TEXT,"+UserMaster.Users.COLUMN_TYPE+" TEXT)";

        String MessageTable = "CREATE TABLE "+UserMaster.Messages.TABLE_NAME+" ("+UserMaster.Messages._ID+" INTEGER PRIMERY KEY,"+
                UserMaster.Messages.COLUMN_USER+" TEXT,"+UserMaster.Messages.COLUMN_SUBJECT+" TEXT,"+UserMaster.Messages.COLUMN_MESSAGE+" TEXT)";

        db.execSQL(UserTable);
        db.execSQL(MessageTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addInfo(String username, String password, String type, String sub, String msg){

        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put(UserMaster.Users.COLUMN_NAME,username);
        value.put(UserMaster.Users.COLUMN_PASSWORD,password);
        value.put(UserMaster.Users.COLUMN_TYPE,type);

        value.put(UserMaster.Messages.COLUMN_USER,username);
        value.put(UserMaster.Messages.COLUMN_SUBJECT,sub);
        value.put(UserMaster.Messages.COLUMN_MESSAGE,msg);

        Long newRowId = db.insert(UserMaster.Users.TABLE_NAME,null,value);
        Long newRowId2 = db.insert(UserMaster.Messages.TABLE_NAME,null,value);
}
    public void readInfo(String userName, String passWord, Context context) {
        SQLiteDatabase db = getReadableDatabase();

        String[] projection = {
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD,
        };

        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor = db.query(
                UserMaster.Users.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        List userNames = new ArrayList<>();
        List passwords = new ArrayList<>();

        while(cursor.moveToNext())
        {
            String username = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String password = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));

            userNames.add(username);
            passwords.add(password);
        }

        cursor.close();
        if(userNames.contains(userName)) {
            if (passwords.contains(passWord)) {
                Toast.makeText(context, "Loging Successfully!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Password is wrong.Try Again.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(context,"Unable to find the User.",Toast.LENGTH_LONG).show();
        }
    }
