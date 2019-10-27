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
import com.ramola.phonebook.databinding.FragmentContactDetailBinding;
import com.ramola.phonebook.db.entity.ContactEntity;
import com.ramola.phonebook.viewmodel.ContactDetailViewModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactDetail extends Fragment {

    private FragmentContactDetailBinding binding;

    public static final String CONTACT_ID = "id";

    public ContactDetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_contact_detail, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ContactDetailViewModel.Factory factory = new ContactDetailViewModel.Factory(requireActivity().getApplication());
        ContactDetailViewModel viewModel = new ViewModelProvider(this, factory).get(ContactDetailViewModel.class);
        subscribeToModel(viewModel);
    }

    private void subscribeToModel(ContactDetailViewModel viewModel) {
        Bundle bundle = getArguments();
        int id = bundle.getInt(CONTACT_ID);
        viewModel.getContact(id).observe(this, new Observer<ContactEntity>() {
            @Override
            public void onChanged(ContactEntity contactEntity) {
                binding.setContact(contactEntity);
            }
        });
    }
}