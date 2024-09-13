package edu.divyagyan.absoluteexampleexam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText num1, num2;
    private Button multiply_btn, divide_btn;
    private TextView result;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.num1);
        num2 = findViewById(R.id.num2);
        result = findViewById(R.id.result);
        multiply_btn = findViewById(R.id.multiply_btn);
        divide_btn = findViewById(R.id.divide_btn);

        multiply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int input1 = Integer.parseInt(num1.getText().toString());
                    int input2 = Integer.parseInt(num2.getText().toString());

                    int multiply = input1 * input2;
                    result.setText("Result: " + multiply);
                } catch (NumberFormatException e) {
                    result.setText("Please enter valid numbers");
                }
            }
        });

        divide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int input1 = Integer.parseInt(num1.getText().toString());
                    int input2 = Integer.parseInt(num2.getText().toString());

                    if (input2 == 0) {
                        result.setText("Cannot divide by zero");
                    } else {
                        int divisionResult = input1 / input2;
                        result.setText("Result: " + divisionResult);
                    }
                } catch (NumberFormatException e) {
                    result.setText("Please enter valid numbers");
                }
            }
        });
    }



}
