package com.ramola.phonebook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.ramola.phonebook.MainApplication;
import com.ramola.phonebook.db.ContactRepository;
import com.ramola.phonebook.model.Contact;

public class AddContactViewModel extends AndroidViewModel {
    private ContactRepository repository;
    public AddContactViewModel(@NonNull Application application) {
        super(application);
        repository = ((MainApplication) application).getRepository();
    }

    public int insertContact(Contact contact) {
        return repository.insertContact(contact);
    }

    public int updateContact(Contact contact) {
        return repository.updateContact(contact);
    }
}
