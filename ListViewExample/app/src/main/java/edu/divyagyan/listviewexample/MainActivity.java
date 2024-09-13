package edu.divyagyan.listviewexample;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // List of programming languages
//        ArrayList<String> programmingLanguages = new ArrayList<>();
//        programmingLanguages.add("Java");
//        programmingLanguages.add("Kotlin");
//        programmingLanguages.add("Python");
//        programmingLanguages.add("JavaScript");
//        programmingLanguages.add("C++");
//        programmingLanguages.add("C#");
//        programmingLanguages.add("Ruby");
//        programmingLanguages.add("PHP");
        String [] programmingLanguages ={"Java","Kotlin","Python","JavaScript","C++","Ruby","PHP"};

        // Find the ListView
        ListView languagesListView = findViewById(R.id.languages_list_view);

        // Create an ArrayAdapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, programmingLanguages);

        // Set the adapter to the ListView
        languagesListView.setAdapter(adapter);
    }
}
