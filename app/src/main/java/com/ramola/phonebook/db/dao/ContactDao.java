package com.ramola.phonebook.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ramola.phonebook.db.entity.ContactEntity;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("Select * from contact")
    LiveData<List<ContactEntity>> loadAllContacts();

    @Query("Select * from contact where id = :contactId")
    LiveData<ContactEntity> loadContact(int contactId);

    @Insert
    void insertContact(ContactEntity contact);

    @Update
    void updateContact(ContactEntity contact);
}
