package com.example.messingaround.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey
    public int uid;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    private String userFirstName;
    private String userLastName;
    public User(String fn, String ln) {
        this.userFirstName = fn;
        this.userLastName = ln;
    }
}