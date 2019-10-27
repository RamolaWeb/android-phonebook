package com.ramola.phonebook.db;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.ramola.phonebook.db.entity.ContactEntity;

import java.util.List;

public class ContactRepository {
    private static ContactRepository repository;
    private AppDatabase database;
    private MediatorLiveData<List<ContactEntity>> observableContacts;

    private ContactRepository(AppDatabase database) {
        this.database = database;
        observableContacts = new MediatorLiveData<>();
        observableContacts.addSource(database.contactDao().loadAllContacts(), new Observer<List<ContactEntity>>() {
            @Override
            public void onChanged(List<ContactEntity> contacts) {
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

    public LiveData<List<ContactEntity>> getAllContact() {
        return observableContacts;
    }

    public LiveData<ContactEntity> getContact(int contactId) {
        return database.contactDao().loadContact(contactId);
    }

    public void insertContact(final ContactEntity contact) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                database.contactDao().insertContact(contact);
            }
        });

    }

    public void updateContact(ContactEntity contact) {
        database.contactDao().updateContact(contact);
    }
}
