package com.ramola.phonebook;

import android.os.Bundle;

import com.ramola.phonebook.databinding.ActivityAddContactBinding;
import com.ramola.phonebook.viewmodel.AddContactViewModel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.view.View;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAddContactBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_add_contact);
        setSupportActionBar(binding.toolbar);
        AddContactViewModel viewModel = new ViewModelProvider().

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

}
