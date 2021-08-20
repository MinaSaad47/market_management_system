package com.example.marketmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewSuppliesActivity extends AppCompatActivity {

    ListView lvSupplies;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_supplies);

        lvSupplies = findViewById(R.id.lvSupplies);

        try {
            SupplyDB db = new SupplyDB(ViewSuppliesActivity.this);
            db.open();
            adapter = new ArrayAdapter<String>(ViewSuppliesActivity.this,
                    android.R.layout.simple_list_item_1, db.getData());
            db.close();
        } catch (SQLException e) {
            Toast.makeText(ViewSuppliesActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        lvSupplies.setAdapter(adapter);

    }
}