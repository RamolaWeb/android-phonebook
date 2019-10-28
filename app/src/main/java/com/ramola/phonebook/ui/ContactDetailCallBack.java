package com.ramola.phonebook.ui;

import com.ramola.phonebook.db.entity.ContactEntity;

public interface ContactDetailCallBack {

    void onCall(ContactEntity contactEntity);

    void onShare(ContactEntity contactEntity);
}
