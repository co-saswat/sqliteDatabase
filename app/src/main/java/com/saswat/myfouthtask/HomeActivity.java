package com.saswat.myfouthtask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.MacAddress;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton actionButton;

    DatabaseHelper dbHelper;
    ArrayList<String> std_id;
    ArrayList<String> std_name;
    ArrayList<String> std_guardian_name;
    ArrayList<String> std_email;
    ArrayList<String> std_phone;

    CustomAdaptor customAdaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        recyclerView = findViewById(R.id.rc_main_view);
        actionButton = findViewById(R.id.add_button);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, RegistryActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DatabaseHelper(HomeActivity.this);
        std_id = new ArrayList<>();
        std_name = new ArrayList<>();
        std_guardian_name = new ArrayList<>();
        std_email = new ArrayList<>();
        std_phone = new ArrayList<>();

        displayData();

        customAdaptor = new CustomAdaptor(HomeActivity.this, this, std_id, std_name, std_guardian_name, std_email, std_phone);
        recyclerView.setAdapter(customAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomeActivity.this));

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    public void displayData() {
        Cursor cursor = dbHelper.retriveDataToDatabase();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "ERROR!!!", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                std_id.add(cursor.getString(0));
                std_name.add(cursor.getString(1));
                std_guardian_name.add(cursor.getString(2));
                std_email.add(cursor.getString(3));
                std_phone.add(cursor.getString(4));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_delete,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("Are you Sure you want to delete all ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper dbHelper = new DatabaseHelper(HomeActivity.this);
                dbHelper.deleteAll();
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}