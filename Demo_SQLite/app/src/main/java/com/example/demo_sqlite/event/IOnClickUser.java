package com.example.demo_sqlite.event;

import com.example.demo_sqlite.enteties.User;

public interface IOnClickUser {
    void updateUser(User user);
    void deleteUser(User user);
}
