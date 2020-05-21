package com.example.a39_day04_demodanhbadt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.a39_day04_demodanhbadt.MainActivity.contactList;

public class registration extends AppCompatActivity {
    Button btnCancel, btnSave, btnTune, btnGroup;
    Spinner spType;
    List<String> listType = new ArrayList<>();
    Contact contact;
    EditText edName, edPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Intent intent = getIntent();
        contact = (Contact) intent.getSerializableExtra("contact");

        setTitle("Add New Contact");
        btnCancel = findViewById(R.id.btnCancel);
        btnSave = findViewById(R.id.btnSave);
        btnTune = findViewById(R.id.btnTune);
        btnGroup = findViewById(R.id.btnGroup);

        spType = findViewById(R.id.spType);
        listType.add("Phone");
        listType.add("Home");

        //set List cho Spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, listType);
        spType.setAdapter(adapter);

        //Chọn nhiều sự kiện bằng checkbox -> BtnGroup
        btnGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] group = {"Family", "Friend", "Stranger", "Co-worker"};
                boolean[] check = {true, false, false, false};
                AlertDialog alertDialog = new AlertDialog.Builder(registration.this).setTitle("Select group")
                        .setMultiChoiceItems(group, check, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i, boolean b) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .create();
                alertDialog.show();
            }
        });


        //Chọn 1 sự kiện -> btnTune
        btnTune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] tune = {"Dancing with your ghost", "Play", "Downtown", "PayPhone"};
                AlertDialog alertDialog = new AlertDialog.Builder(registration.this).setTitle("Select tune")
                        .setSingleChoiceItems(tune, 0, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getBaseContext(), "Yes", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(getBaseContext(), "No", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .create();
                alertDialog.show();
            }
        });
        edName = findViewById(R.id.edName);
        edPhone = findViewById(R.id.edPhone);

        //Ấn Save để add thêm thông tin vào trong MainActivity
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edName.getText().toString();
                String number = String.valueOf(edPhone.getText());
                if (checkName(name) == false) {
                    Toast.makeText(registration.this, "Tên có ít nhất 8 ký tự gồm chữ thường, hoa, số và ký tự đặc biệt", Toast.LENGTH_LONG).show();
                } else {
                    if (checkPhone(number) == false) {
                        Toast.makeText(registration.this, "Số điện thoại chỉ được nhập số", Toast.LENGTH_LONG).show();
                    } else {
                        MainActivity.contactList.clear();
                        Toast.makeText(getBaseContext(), "Thêm thành công! " +
                                "\n" + "Name: " + name
                                + "\n" + "Phone: " + number, Toast.LENGTH_LONG).show();
                        int num = Integer.parseInt(edPhone.getText().toString());
                        MainActivity.contactList.add(new Contact(name, num, true));
                        startActivity(new Intent(getBaseContext(), MainActivity.class));
                    }
                }
            }
        });


        //Sau khi ấn Cancel sẽ quay về màn hình MainActivity
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.contactList.clear();
                startActivity(new Intent(getBaseContext(), MainActivity.class));
            }
        });
    }


    public static boolean checkName(String name) {
        if (name.length() >= 8) {
            Pattern upperLetter = Pattern.compile("[A-Z]");
            Pattern lowletter = Pattern.compile("[a-z]");
            Pattern digit = Pattern.compile("[0-9]");
            Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");

            Matcher hasULetter = upperLetter.matcher(name);
            Matcher hasLLetter = lowletter.matcher(name);
            Matcher hasDigit = digit.matcher(name);
            Matcher hasSpecial = special.matcher(name);

            return hasLLetter.find() && hasULetter.find() && hasDigit.find() && hasSpecial.find();
        } else
            return false;
    }

    public static boolean checkPhone(String number) {
        if (number.length() > 0 && number.length() < 11) {
            Pattern phoneNumber = Pattern.compile("[0-9]");

            Matcher hasPhone = phoneNumber.matcher(number);
            return hasPhone.find();
        } else
            return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.contact_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    // bắt sự kiện onclick menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iSave:
                Toast.makeText(getBaseContext(), "Save", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iCancel:
                Toast.makeText(getBaseContext(), "Cancel", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
