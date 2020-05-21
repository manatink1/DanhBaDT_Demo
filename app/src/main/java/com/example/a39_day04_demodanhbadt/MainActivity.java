package com.example.a39_day04_demodanhbadt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView lvContact;
    static List<Contact> contactList = new ArrayList<>();
    Contact contact1,contact2,contact3,contact4,contact5,contact6,contact7;
    AdapterContact adapterContact;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvContact = findViewById(R.id.lvContact);

        contact1 = new Contact("Nguyễn Quang Đông",1248653,true);
        contact2 = new Contact("Trần Văn Tây",3245682,true);
        contact3 = new Contact("Ngô Bá Nam",2536823,false);
        contact4 = new Contact("Đỗ Văn Bắc",3256823,true);
        contact5 = new Contact("Lê Thị Tạ",5468235,true);
        contact6 = new Contact("Lương Văn Tấn",9369253,false);
        contact7 = new Contact("Hồ Xuân Gam",8668936,true);
        contactList.add(contact1);
        contactList.add(contact2);
        contactList.add(contact3);
        contactList.add(contact4);
        contactList.add(contact5);
        contactList.add(contact6);
        contactList.add(contact7);

        adapterContact = new AdapterContact(contactList);
        lvContact.setAdapter(adapterContact);

        btnAdd = findViewById(R.id.btnAdd);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdd.setTextColor(Color.parseColor("#F8F877"));
                startActivity(new Intent(getBaseContext(),registration.class));
            }
        });

    }
}
