package com.example.a39_day04_demodanhbadt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class AdapterContact extends BaseAdapter {
    List<Contact> contactList = new ArrayList<Contact>();
    TextView tvName,tvPhone;
    ImageView imIcon;

    public AdapterContact(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @Override

    public int getCount() {
        return contactList.size();
    }

    @Override
    public Object getItem(int i) {
        return contactList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        LayoutInflater inflater =LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.string_contact,viewGroup,false);

        Contact contact = contactList.get(i);
        tvName = view.findViewById(R.id.tvName);
        tvPhone = view.findViewById(R.id.tvPhone);
        imIcon = view.findViewById(R.id.imIcon);

        tvName.setText(contact.getName());
        tvPhone.setText(String.valueOf(contact.getNumber()));
        if(contact.isIcon())imIcon.setVisibility(View.VISIBLE);
        else imIcon.setVisibility(View.GONE);

        return view;
    }
}
