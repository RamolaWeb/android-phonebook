package com.ramola.phonebook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.ramola.phonebook.MainApplication;
import com.ramola.phonebook.db.ContactRepository;
import com.ramola.phonebook.db.entity.ContactEntity;

public class AddContactViewModel extends AndroidViewModel {
    private ContactRepository repository;
    public AddContactViewModel(@NonNull Application application) {
        super(application);
        repository = ((MainApplication) application).getRepository();
    }

    public void insertContact(ContactEntity contact) {
        repository.insertContact(contact);
    }

    public void updateContact(ContactEntity contact) {
        repository.updateContact(contact);
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application application;

        public Factory(Application application) {
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new AddContactViewModel(application);
        }
    }
}
