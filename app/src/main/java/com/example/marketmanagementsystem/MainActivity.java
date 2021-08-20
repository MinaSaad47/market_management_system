package com.example.marketmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAdd, btnViewSupplies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnViewSupplies = findViewById(R.id.btnViewSupplies);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(MainActivity.this,
                        com.example.marketmanagementsystem.AddActivity.class);
                startActivity(addIntent);
            }
        });

        btnViewSupplies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent viewSuppliesIntent = new Intent(MainActivity.this,
                        com.example.marketmanagementsystem.ViewSuppliesActivity.class);
                startActivity(viewSuppliesIntent);
            }
        });
    }
}