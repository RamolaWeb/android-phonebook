package com.ramola.phonebook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.ramola.phonebook.MainApplication;
import com.ramola.phonebook.db.ContactRepository;
import com.ramola.phonebook.db.entity.ContactEntity;

public class ContactDetailViewModel extends AndroidViewModel {
    private ContactRepository repository;
    private MediatorLiveData<ContactEntity> observableContact;

    public ContactDetailViewModel(@NonNull Application application) {
        super(application);
        repository = ((MainApplication) application).getRepository();
    }

    public LiveData<ContactEntity> getContact(int id) {
        return repository.getContact(id);
    }
}
