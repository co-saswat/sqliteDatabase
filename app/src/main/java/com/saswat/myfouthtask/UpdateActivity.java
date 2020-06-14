package com.saswat.myfouthtask;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {
    EditText name_input;
    EditText parent_input;
    EditText email_input;
    EditText phone_input;
    Button update;
    Button delete;

    String id, name, parent_name, email, phone_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name_input = findViewById(R.id.et_std_name_updt);
        parent_input = findViewById(R.id.et_guardians_name_updt);
        email_input = findViewById(R.id.et_email_address_updt);
        phone_input = findViewById(R.id.et_phone_no_updt);
        update = findViewById(R.id.button_update);
        delete = findViewById(R.id.button_delete);
        getAndSetIntentData();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(name);
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(UpdateActivity.this);
                name = name_input.getText().toString().trim();
                parent_name = parent_input.getText().toString().trim();
                email = email_input.getText().toString().trim();
                phone_no = phone_input.getText().toString().trim();
                dbHelper.updateData(id, name, parent_name, email, phone_no);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });


    }

    public void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("parent") &&
                getIntent().hasExtra("email") && getIntent().hasExtra("phone")) {
            //Getting Data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            parent_name = getIntent().getStringExtra("parent");
            email = getIntent().getStringExtra("email");
            phone_no = getIntent().getStringExtra("phone");
            //Setting Data
            name_input.setText(name);
            parent_input.setText(parent_name);
            email_input.setText(email);
            phone_input.setText(phone_no);
        } else {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + "?");
        builder.setMessage("Are you Sure you want to delete " + name + "?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbHelper = new DatabaseHelper(UpdateActivity.this);
                dbHelper.deleteOneRowData(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(UpdateActivity.this, "Sorry!!!", Toast.LENGTH_SHORT).show();
            }
        });
        builder.create().show();
    }
}