package com.ramola.phonebook.db;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.ramola.phonebook.model.Contact;

import java.util.List;

public class ContactRepository {
    private static ContactRepository repository;
    private AppDatabase database;
    private MediatorLiveData<List<Contact>> observableContacts;

    private ContactRepository(AppDatabase database) {
        this.database = database;
        observableContacts.addSource(database.contactDao().loadAllContacts(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                observableContacts.postValue(contacts);
            }
        });
    }

    public static ContactRepository getInstance(AppDatabase database) {
        if (repository == null) {
            synchronized (ContactRepository.class) {
                if (repository == null) {
                    repository = new ContactRepository(database);
                }
            }
        }
        return repository;
    }

    public LiveData<List<Contact>> getAllContact() {
        return observableContacts;
    }

    public LiveData<Contact> getContact(int contactId) {
        return database.contactDao().loadContact(contactId);
    }

    public int insertContact(Contact contact) {
        return  database.contactDao().insertContact(contact);
    }

    public int updateContact(Contact contact) {
        return  database.contactDao().updateContact(contact);
    }
}
