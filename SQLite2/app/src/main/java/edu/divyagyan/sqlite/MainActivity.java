package edu.divyagyan.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DBHelper dbHelper;
    private ListView listView;
    private PersonAdapter personAdapter;
    private List<Person> personList;
    private EditText nameEditText, addressEditText;
    private Button addButton, updateButton, deleteButton;

    private int selectedPersonId = -1; // Track the selected person for update/delete

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        listView = findViewById(R.id.listView);
        nameEditText = findViewById(R.id.nameEditText);
        addressEditText = findViewById(R.id.addressEditText);
        addButton = findViewById(R.id.addButton);
        updateButton = findViewById(R.id.updateButton);
        deleteButton = findViewById(R.id.deleteButton);

        personList = new ArrayList<>();
        personAdapter = new PersonAdapter(this, personList);
        listView.setAdapter(personAdapter);

        loadPeople();

        addButton.setOnClickListener(v -> addPerson());
        updateButton.setOnClickListener(v -> updatePerson());
        deleteButton.setOnClickListener(v -> deletePerson());

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Person person = personList.get(position);
            nameEditText.setText(person.getName());
            addressEditText.setText(person.getAddress());
            selectedPersonId = person.getId();
        });
    }

    private void loadPeople() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("people", null, null, null, null, null, null);

        personList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String address = cursor.getString(cursor.getColumnIndex("address"));

            personList.add(new Person(id, name, address));
        }
        cursor.close();
        personAdapter.notifyDataSetChanged();
    }

    private void addPerson() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", nameEditText.getText().toString());
        values.put("address", addressEditText.getText().toString());

        long newRowId = db.insert("people", null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Person added", Toast.LENGTH_SHORT).show();
            loadPeople();
        } else {
            Toast.makeText(this, "Error adding person", Toast.LENGTH_SHORT).show();
        }
    }

    private void updatePerson() {
        if (selectedPersonId == -1) {
            Toast.makeText(this, "Select a person to update", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", nameEditText.getText().toString());
        values.put("address", addressEditText.getText().toString());

        int rowsAffected = db.update("people", values, "_id = ?", new String[]{String.valueOf(selectedPersonId)});
        if (rowsAffected > 0) {
            Toast.makeText(this, "Person updated", Toast.LENGTH_SHORT).show();
            loadPeople();
            selectedPersonId = -1; // Reset selection
        } else {
            Toast.makeText(this, "Error updating person", Toast.LENGTH_SHORT).show();
        }
    }

    private void deletePerson() {
        if (selectedPersonId == -1) {
            Toast.makeText(this, "Select a person to delete", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = db.delete("people", "_id = ?", new String[]{String.valueOf(selectedPersonId)});
        if (rowsDeleted > 0) {
            Toast.makeText(this, "Person deleted", Toast.LENGTH_SHORT).show();
            loadPeople();
            selectedPersonId = -1; // Reset selection
        } else {
            Toast.makeText(this, "Error deleting person", Toast.LENGTH_SHORT).show();
        }
    }
}

