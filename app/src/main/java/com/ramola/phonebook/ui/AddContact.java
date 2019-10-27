package com.ramola.phonebook.ui;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
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
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = binding.contentAddContact.nameInput.getText().toString();
                String email = binding.contentAddContact.emailInput.getText().toString();
                String company = binding.contentAddContact.companyInput.getText().toString();
                String contact = binding.contentAddContact.phoneInput.getText().toString();

                if (!name.isEmpty() && !email.isEmpty() && !company.isEmpty() && !contact.isEmpty()) {
                    ContactEntity contactEntity = new ContactEntity(name, email, contact, company);
                    model.insertContact(contactEntity);
                }
            }
        });
    }
}
