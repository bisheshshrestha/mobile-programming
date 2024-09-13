package edu.divyagyan.sqliteexamplehome;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    //widget
    private EditText idEdit,nameEdit,emailEdit;
    private Button addButton,deleteButton,showButton, showAllButton, deleteAllButton,updateButton;
    private DatabaseHelper myDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize DatabaseHelper
        myDB = new DatabaseHelper(MainActivity.this);

        idEdit = findViewById(R.id.idid);
        nameEdit = findViewById(R.id.name);
        emailEdit = findViewById(R.id.email);

        addButton = findViewById(R.id.addbtn);
        deleteAllButton = findViewById(R.id.deleteallbtn);
        deleteButton = findViewById(R.id.deletebtn);
        showAllButton = findViewById(R.id.showallbtn);
        showButton = findViewById(R.id.showbtn);
        updateButton = findViewById(R.id.updatebtn);


        addData();
        showData();
    }

    public void addData(){
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDB.insertData(nameEdit.getText().toString() , emailEdit.getText().toString());

                if (isInserted){
                    Toast.makeText(MainActivity.this, "Data Inserted...", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(MainActivity.this, "Something went Wrong", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public  void showData(){
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id =idEdit.getText().toString();
                if(id.equals(String.valueOf(""))){
                    idEdit.setError("Please Enter id");
                }
                    Cursor cursor = myDB.getData(id);
                    String data = null;
                if (cursor.moveToNext()){
                    data = "ID : " + cursor.getString(0) + "\n" +
                            "NAME : " + cursor.getString(1) + "\n" +
                            "EMAIL : " + cursor.getString(2) + "\n" ;
                    showMessage("DATA" , data);
                }else{
                    showMessage("DATA" , "There is no Data");
                }
            }
        });
    }

    public void showMessage(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
