package com.ramola.phonebook;

import android.app.Application;

import com.ramola.phonebook.db.AppDatabase;
import com.ramola.phonebook.db.ContactRepository;

public class MainApplication extends Application {

    public AppDatabase getDatabase() {
        return  AppDatabase.getInstance(this);
    }

    public ContactRepository getRepository() {
        return ContactRepository.getInstance(getDatabase());
    }
}
