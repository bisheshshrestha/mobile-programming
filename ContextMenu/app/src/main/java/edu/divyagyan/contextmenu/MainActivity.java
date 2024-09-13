package edu.divyagyan.contextmenu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootLayout = findViewById(R.id.root_layout);

        // Register the layout for context menu
        registerForContextMenu(rootLayout);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.color_menu,menu);
//        getMenuInflater().inflate(R.menu.color_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.red) {
            rootLayout.setBackgroundColor(Color.RED);
            return true;
        } else if (id == R.id.yellow) {
            rootLayout.setBackgroundColor(Color.YELLOW);
            return true;
        } else if (id == R.id.green) {
            rootLayout.setBackgroundColor(Color.GREEN);
            return true;
        } else if (id == R.id.black) {
            rootLayout.setBackgroundColor(Color.BLACK);
            return true;
        } else if (id == R.id.blue) {
            rootLayout.setBackgroundColor(Color.BLUE);
            return true;
        } else {
            return super.onContextItemSelected(item);
        }
    }

}
