package com.example.career_match;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class Edit_Qualifications extends AppCompatActivity {
    Spinner s1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_qualifications);

        s1 = findViewById(R.id.spinner2);
        b1 = findViewById(R.id.button7);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String qual = s1.toString();
            }
        });
    }
}