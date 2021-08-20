package com.example.marketmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity {

    ListView lvItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        lvItems = findViewById(R.id.lvItems);

        ArrayAdapter adapter = new ArrayAdapter<String>(AddActivity.this,
                android.R.layout.simple_list_item_1, SupplyDB.itemsName);

        lvItems.setAdapter(adapter);

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(AddActivity.this, SupplyDB.itemsName[i], Toast.LENGTH_SHORT).show();

                Intent addContIntent = new Intent(AddActivity.this,
                        com.example.marketmanagementsystem.AddContActivity.class);

                addContIntent.putExtra("data", SupplyDB.itemsName[i]);
                startActivity(addContIntent);
            }
        });

    }
}