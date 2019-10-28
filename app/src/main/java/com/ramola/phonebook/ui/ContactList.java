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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ramola.phonebook.R;
import com.ramola.phonebook.databinding.ContactListBinding;
import com.ramola.phonebook.db.entity.ContactEntity;
import com.ramola.phonebook.viewmodel.ContactListViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContactList extends Fragment {

    private ContactListBinding binding;
    private ContactListAdapter adapter;

    public ContactList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.contact_list, container, false);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        binding.list.setLayoutManager(layoutManager);

        adapter = new ContactListAdapter(new ContactClickCallBack() {
            @Override
            public void onClick(ContactEntity contactEntity) {
                ContactDetail detailFragment = new ContactDetail();
                Bundle bundle = new Bundle();
                bundle.putInt(ContactDetail.CONTACT_ID, contactEntity.getId());
                detailFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(ContactList.class.getSimpleName()).replace(R.id.frame_container, detailFragment, ContactDetail.class.getSimpleName()).commit();
            }
        });

        binding.list.setAdapter(adapter);

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(ContactDetail.MODE, "add");
                AddContact contactFragment = new AddContact();
                contactFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(ContactList.class.getSimpleName()).replace(R.id.frame_container, contactFragment, AddContact.class.getSimpleName()).commit();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ContactListViewModel.Factory factory = new ContactListViewModel.Factory(requireActivity().getApplication());
        ContactListViewModel viewModel = new ViewModelProvider(this, factory).get(ContactListViewModel.class);
        subscribeViewModel(viewModel);
    }

    private void subscribeViewModel(ContactListViewModel viewModel) {
        viewModel.getContactList().observe(this, new Observer<List<ContactEntity>>() {
            @Override
            public void onChanged(List<ContactEntity> contactEntities) {
                if (contactEntities != null) {
                    adapter.setContactList(contactEntities);
                }
            }
        });
    }
}
