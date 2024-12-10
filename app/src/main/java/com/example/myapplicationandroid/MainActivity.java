package com.example.myapplicationandroid;

import static android.widget.Toast.LENGTH_LONG;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStructure;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button select = findViewById(R.id.Select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String[] items = {"Item 1", "Item 2", "Item 3", "Item 4"};
                final boolean[] selectedItems = {false, false, false, false}; // Initial state of checkboxes

                // Create AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Select Items")
                        .setMultiChoiceItems(items, selectedItems, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                // Handle individual item clicks (optional)
                            }
                        })
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // After clicking OK, display selected items
                                StringBuilder selectedItemsList = new StringBuilder();
                                for (int i = 0; i < items.length; i++) {
                                    if (selectedItems[i]) {
                                        selectedItemsList.append(items[i]).append("\n");
                                    }
                                }

                              Toast.makeText(MainActivity.this, "Selected Items:\n" + selectedItemsList.toString(), LENGTH_LONG).show();
                            }
                        })
                        .setNegativeButton("Cancel", null);

                // Show the dialog
                builder.create().show();

            }
        });
        Button date = findViewById(R.id.Date);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a Calendar instance to get the current date
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                // Create DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                // Format the date as you want (for example, YYYY-MM-DD)
                                Toast.makeText( MainActivity.this, year + "-" + (monthOfYear + 1) + "-" + dayOfMonth, LENGTH_LONG).show();
                            }
                        },
                        year, month, dayOfMonth);

                // Show the DatePickerDialog
                datePickerDialog.show();
            }
        });

    }
}