package com.ramola.phonebook.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ColumnInfo;

import com.ramola.phonebook.model.Contact;

@Entity(tableName = "contact")
public class ContactEntity implements Contact {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "email")
    private String email;
    @ColumnInfo(name = "contact")
    private String contact;

    @ColumnInfo(name = "company")
    private String company;

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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public ContactEntity() {
        this("","", "", "");
    }

    public ContactEntity(String name, String email, String contact, String company) {
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.company = company;
    }

    public ContactEntity(int id, String name, String email, String contact, String company) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contact = contact;
        this.company = company;
    }
}
