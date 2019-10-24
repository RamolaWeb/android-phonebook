package com.ramola.phonebook.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ramola.phonebook.model.Contact;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("Select * from contact")
    LiveData<List<Contact>> loadAllContacts();

    @Query("Select * from contact where id = :contactId")
    LiveData<Contact> loadContact(int contactId);

    @Insert
    int insertContact(Contact contact);

    @Update
    int updateContact(Contact contact);
}
