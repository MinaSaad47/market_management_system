package com.example.marketmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddContActivity extends AppCompatActivity {

    TextView tvSelectedItem;
    EditText etAddPrice, etAddQuantity;
    Button btnAddSubmit;
    String itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cont);

        tvSelectedItem = findViewById(R.id.tvSelectedItem);
        etAddPrice = findViewById(R.id.etAddPrice);
        etAddQuantity = findViewById(R.id.etAddQuantity);
        btnAddSubmit = findViewById(R.id.btnAddSubmit);

        itemName = getIntent().getStringExtra("data").toString();
        tvSelectedItem.setText(itemName);

        btnAddSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String price = etAddPrice.getText().toString().trim();
                String quantity = etAddQuantity.getText().toString().trim();

                SupplyDB db = new SupplyDB(AddContActivity.this);
                try {
                    db.open();
                    db.updateEntry(itemName, price, quantity);
                    db.close();
                    Toast.makeText(AddContActivity.this, "Successfully added an entry!",
                            Toast.LENGTH_SHORT).show();
                } catch (SQLException e) {
                    Toast.makeText(AddContActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}