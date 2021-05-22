package com.example.buoi6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "learnSQLite";//tên DB
    static final int DB_VERSION = 1;        //version
    static final String DB_TABLE_NAME = "Accounts";
    SQLiteDatabase sqLiteDatabase; //thao tác với cơ sở dữ liệu
    ContentValues contentValues; //cung cấp đối tượng để ném vào data

    public SQLHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public SQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //tạo bảng: AUTOINCREMENT tự động tăng id
        String query = "CREATE TABLE " + DB_TABLE_NAME +
                " ( id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "username TEXT, " +
                "password TEXT)";
        db.execSQL(query); //thực thi
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //nếu 2 version thay đổi thì reset database luôn
        if(oldVersion != newVersion){
            db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE_NAME);
            onCreate(db);
        }
    }

    //thao tác vs DB
    public void insertAccount(Account account){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("username", account.getUsername());
        contentValues.put("password", account.getPassword());
        sqLiteDatabase.insert(DB_TABLE_NAME, null, contentValues); //insert vào table
    }

    public void updateAccount(int id, Account account){
        sqLiteDatabase = getWritableDatabase();
        contentValues = new ContentValues();
        contentValues.put("username", account.getUsername());
        contentValues.put("password", account.getPassword());
        sqLiteDatabase.update(DB_TABLE_NAME, contentValues, "id=?", new String[]{String.valueOf(id)});
//        sqLiteDatabase.update(DB_TABLE_NAME, contentValues, "id=?", new String[]{id+""});
    }

    public void deleteAccount(int id){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_NAME, "id=?", new String[] {String.valueOf(id)});
    }

    public void deleteAllAccount(){
        sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.delete(DB_TABLE_NAME, null, null);
    }

    public List<Account> getAllAccount(){
        List<Account> list = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(DB_TABLE_NAME, null, null, null, null, null, null);
        while(cursor.moveToNext()){
            String user = cursor.getString(cursor.getColumnIndex("username"));
            String pass = cursor.getString(cursor.getColumnIndex("password"));
            list.add(new Account(user, pass));
        }

        return list;
    }

    public boolean checkExists(int id){
        sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + DB_TABLE_NAME + " WHERE id=?", new String[]{String.valueOf(id)});
        if(cursor.getCount() == 1)
            return true;
        return false;
    }

}
