package edu.divyagyan.simplegridviewexample;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String [] items = { "Item 1", "Item 2", "Item 3",
                "Item 4", "Item 5", "Item 6",
                "Item 7", "Item 8", "Item 9"};

        GridView gridView = findViewById(R.id.gridView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);

        gridView.setAdapter(arrayAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String value =arrayAdapter.getItem(position);
                Toast.makeText(getApplicationContext(),value, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
