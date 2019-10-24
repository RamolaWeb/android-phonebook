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
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public String getContact() {
        return contact;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
