package com.example.career_match;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Edit_Preferences extends AppCompatActivity {
    Spinner s1;
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_preferences);

        s1 = findViewById(R.id.spinner4);
        b1 = findViewById(R.id.button11);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pref = s1.toString();
            }
        });
    }
}