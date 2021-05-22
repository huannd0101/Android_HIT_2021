package com.example.demo_sqlite.DAO;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.demo_sqlite.enteties.User;
/*
* thêm cột thì cần update version
* và khai báo migration để update
* xong vào trong hàm getInstace add thêm migration
* */
@Database(entities={User.class}, version = 2)
public abstract class UserDatabase extends RoomDatabase {
    /*
    * Thêm nhiều cột: alter table ten_bang add cot_1 dinh_nghia_cot_1, cot_2 dinh_nghia_cot_2
    * Chỉnh sửa kiểu dữ liệu: alter table ten_bang alter column ten_cot kieu_cot;
    * Xóa cột trong bảng: alter table ten_bang drop column ten_cot;
    * */


    //sau khi thêm cột cần
    static Migration migration_from_1_to_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("ALTER TABLE user ADD COLUMN dateOfBirthday TEXT");
        }
    };

    private static final String DATABASE_NAME = "demo";

    private static UserDatabase instance;

//   synchronized: tuần tự
    public static synchronized UserDatabase getInstance(Context context){
        if(instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .addMigrations(migration_from_1_to_2)
                    .build();
        return instance;
    }

    public abstract UserDAO userDAO();
}
