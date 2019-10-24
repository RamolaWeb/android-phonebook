package com.ramola.phonebook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.ramola.phonebook.MainApplication;
import com.ramola.phonebook.db.ContactRepository;
import com.ramola.phonebook.model.Contact;

import java.util.List;

public class ContactListViewModel extends AndroidViewModel {

    private ContactRepository repository;
    private MediatorLiveData<List<Contact>> observerContactList;

    public ContactListViewModel(@NonNull Application application) {
        super(application);
        repository = ((MainApplication) application).getRepository();
        observerContactList.addSource(repository.getAllContact(), new Observer<List<Contact>>() {
            @Override
            public void onChanged(List<Contact> contacts) {
                observerContactList.setValue(contacts);
            }
        });
    }

    public LiveData<List<Contact>> getContactList() {
        return observerContactList;
    }
}
