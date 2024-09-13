package edu.divyagyan.customdialogexamplehome;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button customDialogButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        customDialogButton = findViewById(R.id.customDialogButton);

        customDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog();
            }
        });
    }

    public void customDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Inflate the custom layout
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_custom, null);
        builder.setView(dialogView);

        // Find EditText fields and result TextView in the dialog layout
        EditText firstNumberInputEditText = dialogView.findViewById(R.id.firstNumberInputEditText);
        EditText secondNumberInputEditText = dialogView.findViewById(R.id.secondNumberInputEditText);
        Button calculateButton = dialogView.findViewById(R.id.calculateButton);
        TextView resultTextView = dialogView.findViewById(R.id.resultTextView);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input values
                int num1 = Integer.parseInt(firstNumberInputEditText.getText().toString());
                int num2 = Integer.parseInt(secondNumberInputEditText.getText().toString());

                // Calculate the sum
                int sum = num1 + num2;

                // Set the result in the TextView within the dialog
                resultTextView.setText("Result: " + sum);

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
