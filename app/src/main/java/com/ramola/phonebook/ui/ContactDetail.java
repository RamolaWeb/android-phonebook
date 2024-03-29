package com.ramola.phonebook.ui;


import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
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
    public static final String EDIT_MODE = "edit";
    public static final String MODE = "mode";

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
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getArguments().getInt(CONTACT_ID);
                AddContact addFragment = new AddContact();
                Bundle bundle = new Bundle();
                bundle.putInt(ContactDetail.CONTACT_ID, id);
                bundle.putString(MODE, EDIT_MODE);
                addFragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack(ContactDetail.class.getSimpleName())
                        .replace(R.id.frame_container, addFragment, AddContact.class.getSimpleName()).commit();
            }
        });
        binding.setCallback(new ContactDetailCallBack() {
            @Override
            public void onCall(ContactEntity contactEntity) {
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.CALL_PHONE)
                            == PackageManager.PERMISSION_GRANTED) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+contactEntity.getContact()));//change the number
                        startActivity(callIntent);
                    } else {

                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }
                }
                else { //permission is automatically granted on sdk<23 upon installation
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:"+contactEntity.getContact()));//change the number
                    startActivity(callIntent);
                }

            }

            @Override
            public void onShare(ContactEntity contactEntity) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Checkout the number of "+contactEntity.getName()+" "+contactEntity.getContact();
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Contact");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
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
