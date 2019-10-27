package com.ramola.phonebook.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.ramola.phonebook.MainApplication;
import com.ramola.phonebook.db.ContactRepository;
import com.ramola.phonebook.db.entity.ContactEntity;

import java.util.List;

public class ContactListViewModel extends AndroidViewModel {

    private ContactRepository repository;
    private MediatorLiveData<List<ContactEntity>> observerContactList;

    public ContactListViewModel(@NonNull Application application) {
        super(application);
        repository = ((MainApplication) application).getRepository();
        observerContactList = new MediatorLiveData<>();
        observerContactList.addSource(repository.getAllContact(), new Observer<List<ContactEntity>>(){
            @Override
            public void onChanged(List<ContactEntity> contacts) {
                observerContactList.setValue(contacts);
            }
        });
    }

    public LiveData<List<ContactEntity>> getContactList() {
        return observerContactList;
    }

    public static class Factory extends ViewModelProvider.NewInstanceFactory {
        private final Application application;

        public Factory(Application application) {
            this.application = application;
        }

        @NonNull
        @Override
        public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
            return (T) new ContactListViewModel(application);
        }
    }
}
