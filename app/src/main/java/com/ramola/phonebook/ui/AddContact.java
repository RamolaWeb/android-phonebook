package com.ramola.phonebook.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.ramola.phonebook.R;
import com.ramola.phonebook.databinding.ActivityAddContactBinding;
import com.ramola.phonebook.db.entity.ContactEntity;
import com.ramola.phonebook.viewmodel.AddContactViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddContact extends Fragment {


    private ActivityAddContactBinding binding;
    public AddContact() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.activity_add_contact, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        AddContactViewModel.Factory factory = new AddContactViewModel.Factory(requireActivity().getApplication());
        final AddContactViewModel model = new ViewModelProvider(this, factory).get(AddContactViewModel.class);
        final Bundle bundle = getArguments();
        final String mode = bundle.getString(ContactDetail.MODE, "add");
        if (mode == ContactDetail.EDIT_MODE) {
            int id = bundle.getInt(ContactDetail.CONTACT_ID);
            model.getContact(id).observe(this, new Observer<ContactEntity>() {
                @Override
                public void onChanged(ContactEntity contactEntity) {
                    binding.contentAddContact.setContact(contactEntity);
                }
            });
        }
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.contentAddContact.nameInput.getText().toString();
                String email = binding.contentAddContact.emailInput.getText().toString();
                String company = binding.contentAddContact.companyInput.getText().toString();
                String contact = binding.contentAddContact.phoneInput.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !company.isEmpty() && !contact.isEmpty()) {

                    if (mode == ContactDetail.EDIT_MODE) {
                        int id = bundle.getInt(ContactDetail.CONTACT_ID);
                        ContactEntity contactEntity = new ContactEntity(id, name, email, contact, company);
                        model.updateContact(contactEntity);
                    }
                    else {
                        ContactEntity contactEntity = new ContactEntity(name, email, contact, company);
                        model.insertContact(contactEntity);
                    }
                }
            }
        });
    }
}
