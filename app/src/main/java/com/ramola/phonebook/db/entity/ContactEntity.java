package com.ramola.phonebook.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.ramola.phonebook.model.Contact;

@Entity(tableName = "contact")
public class ContactEntity implements Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String email;
    private String contact;

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getContact() {
        return null;
    }
}
