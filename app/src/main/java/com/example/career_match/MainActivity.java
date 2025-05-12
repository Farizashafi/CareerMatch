package com.example.career_match;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button b1;
    SharedPreferences sh;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        e1 = findViewById(R.id.editTextTextPersonName3);
        e1.setText(sh.getString("ip", ""));
        b1 = findViewById(R.id.button12);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ip = e1.getText().toString();
                String url="http://"+ip+":8001";
                SharedPreferences.Editor ed=sh.edit();
                ed.putString("ip",ip);
                ed.putString("url",url);
                ed.commit();
                Intent i =new Intent(getApplicationContext(),Login.class);
                startActivity(i);


            }
        });
    }
}