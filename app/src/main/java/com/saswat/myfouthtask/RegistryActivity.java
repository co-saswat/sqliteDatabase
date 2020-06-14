package com.saswat.myfouthtask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistryActivity extends AppCompatActivity {
    EditText et_std_name;
    EditText et_std_guardian_name;
    EditText et_std_email;
    EditText et_std_phone;
    Button btn_add_std;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);

        et_std_name = findViewById(R.id.et_std_name_updt);
        et_std_guardian_name = findViewById(R.id.et_guardians_name_updt);
        et_std_email = findViewById(R.id.et_email_address_updt);
        et_std_phone = findViewById(R.id.et_phone_no_updt);
        btn_add_std = findViewById(R.id.button_add);
        btn_add_std.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper dbHelper = new DatabaseHelper(RegistryActivity.this);
                dbHelper.addDataToDatabase(et_std_name.getText().toString().trim(),
                        et_std_guardian_name.getText().toString().trim(),
                        et_std_email.getText().toString().trim(),
                        Integer.parseInt(et_std_phone.getText().toString().trim()));
            }
        });
    }
}