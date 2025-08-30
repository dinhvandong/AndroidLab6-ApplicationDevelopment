package com.app_mobile.eventhandlingdemo;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button btnToast, btnDialog, btnDatePicker, btnTimePicker;
    Spinner spinner;
    ListView listView, customListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToast = findViewById(R.id.btnToast);
        btnDialog = findViewById(R.id.btnDialog);
        btnDatePicker = findViewById(R.id.btnDatePicker);
        btnTimePicker = findViewById(R.id.btnTimePicker);
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        customListView = findViewById(R.id.customListView);

        // 1. Toast
        btnToast.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "This is a Toast message!", Toast.LENGTH_SHORT).show()
        );

        // 2. AlertDialog
        btnDialog.setOnClickListener(v -> {
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Confirmation")
                    .setMessage("Do you want to proceed?")
                    .setPositiveButton("OK", (dialog, which) ->
                            Toast.makeText(MainActivity.this, "You clicked OK", Toast.LENGTH_SHORT).show())
                    .setNegativeButton("Cancel", (dialog, which) ->
                            Toast.makeText(MainActivity.this, "You clicked Cancel", Toast.LENGTH_SHORT).show())
                    .show();
        });

        // 3. Spinner
        String[] items = {"Option 1", "Option 2", "Option 3"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "Selected: " + items[position], Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 4. Basic ListView
        String[] fruits = {"Apple", "Banana", "Orange", "Mango"};
        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fruits);
        listView.setAdapter(listAdapter);

        listView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(MainActivity.this, "Clicked: " + fruits[position], Toast.LENGTH_SHORT).show()
        );

        // 4. Custom ListView
        String[] animals = {"Dog", "Cat", "Elephant", "Lion"};
        int[] images = {R.drawable.dog, R.drawable.cat, R.drawable.elephant, R.drawable.lion};

        CustomAdapter customAdapter = new CustomAdapter(this, animals, images);
        customListView.setAdapter(customAdapter);

        customListView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(MainActivity.this, "Selected: " + animals[position], Toast.LENGTH_SHORT).show()
        );

        // 5. DatePicker
        btnDatePicker.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity.this,
                    (view, y, m, d) -> Toast.makeText(MainActivity.this,
                            "Date: " + d + "/" + (m + 1) + "/" + y,
                            Toast.LENGTH_SHORT).show(),
                    year, month, day);
            datePickerDialog.show();
        });

        // 6. TimePicker
        btnTimePicker.setOnClickListener(v -> {
            Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    MainActivity.this,
                    (view, h, m) -> Toast.makeText(MainActivity.this,
                            "Time: " + h + ":" + m,
                            Toast.LENGTH_SHORT).show(),
                    hour, minute, true);
            timePickerDialog.show();
        });
    }
}