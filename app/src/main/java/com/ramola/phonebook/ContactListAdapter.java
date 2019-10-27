package com.ramola.phonebook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.ramola.phonebook.databinding.ItemContactBinding;
import com.ramola.phonebook.db.entity.ContactEntity;

import java.util.ArrayList;
import java.util.List;

public class ContactListAdapter extends RecyclerView.Adapter<ContactListAdapter.ViewHolder>{

    private List<ContactEntity> list = new ArrayList<>();

    public void setContactList(List<ContactEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemContactBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_contact, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.binding.setProduct(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ItemContactBinding binding;
        public ViewHolder(ItemContactBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
