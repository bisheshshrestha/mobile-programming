package edu.divyagyan.fragmentclasshome;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add Fragment to the aactivity
        getSupportFragmentManager().beginTransaction()
        .add(R.id.fragmentContainer,new MyFragment())
        .commit();
    }
}
