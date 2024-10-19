package com.example.smsrecive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PhoneNumberAdapter extends RecyclerView.Adapter<PhoneNumberAdapter.ViewHolder> {
    private List<String> phoneNumbers;

    public PhoneNumberAdapter(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone_number, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.phoneNumberTextView.setText(phoneNumbers.get(position));
    }

    @Override
    public int getItemCount() {
        return phoneNumbers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView phoneNumberTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
        }
    }
}