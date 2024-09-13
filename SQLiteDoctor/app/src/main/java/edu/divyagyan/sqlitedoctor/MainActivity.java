package edu.divyagyan.sqlitedoctor;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private Button buttonInsert, buttonDisplay;
    private TextView textViewDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        buttonInsert = findViewById(R.id.button_insert);
        buttonDisplay = findViewById(R.id.button_display);
        textViewDisplay = findViewById(R.id.textView_display);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertDoctorRecords();
            }
        });

        buttonDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayDoctorRecords();
            }
        });
    }

    private void insertDoctorRecords() {
        boolean isInserted1 = myDb.insertData("Dr. John Smith", "Cardiology", 6.0);
        boolean isInserted2 = myDb.insertData("Dr. Jane Doe", "Neurology", 4.5);
        boolean isInserted3 = myDb.insertData("Dr. Emily Davis", "Orthopedics", 3.0);
        boolean isInserted4 = myDb.insertData("Dr. Michael Brown", "Pediatrics", 7.0);
        boolean isInserted5 = myDb.insertData("Dr. Sarah Johnson", "Dermatology", 2.5);

        if (isInserted1 && isInserted2 && isInserted3 && isInserted4 && isInserted5) {
            Toast.makeText(MainActivity.this, "Records Inserted Successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Insertion Failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayDoctorRecords() {
        Cursor res = myDb.getDoctorsWithExperienceLessThan(5.5);
        if (res.getCount() == 0) {
            // Show message if no data is found
            textViewDisplay.setText("No data found");
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()) {
            buffer.append("Did: ").append(res.getInt(0)).append("\n");
            buffer.append("Name: ").append(res.getString(1)).append("\n");
            buffer.append("Specialization: ").append(res.getString(2)).append("\n");
            buffer.append("Experience: ").append(res.getDouble(3)).append("\n\n");
        }

        // Display the doctor records
        textViewDisplay.setText(buffer.toString());
    }
}
