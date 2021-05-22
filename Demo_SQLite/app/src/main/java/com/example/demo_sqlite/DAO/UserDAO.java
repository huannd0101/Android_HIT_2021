package com.example.demo_sqlite.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.demo_sqlite.enteties.User;

import java.util.List;

@Dao
public interface UserDAO {
    //chứa những phương thức để truy cập cơ sở dữ liệu

    //insert
    @Insert
    void insertUser(User user);

    //get cả 1 list user
    @Query("SELECT * FROM user")
    List<User> getListUser();


    @Query("SELECT * FROM user WHERE name = :username")
    List<User> checkUser(String username);

    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM user")
    void deleteAllUser();

    @Query("SELECT * FROM user WHERE name LIKE '%'|| :username ||'%'")
    List<User> searchByUsername(String username);
}
