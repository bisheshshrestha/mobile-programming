package edu.divyagyan.customdialogbox;

// MainActivity.java
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openDialogButton = findViewById(R.id.open_dialog_button);
        openDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog();
            }
        });
    }

    private void showCustomDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.custom_dialog);
        dialog.setCancelable(true);

        EditText principalInput = dialog.findViewById(R.id.principal_input);
        EditText rateInput = dialog.findViewById(R.id.rate_input);
        EditText timeInput = dialog.findViewById(R.id.time_input);
        Button calculateButton = dialog.findViewById(R.id.calculate_button);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String principalStr = principalInput.getText().toString();
                String rateStr = rateInput.getText().toString();
                String timeStr = timeInput.getText().toString();

                if (principalStr.isEmpty() || rateStr.isEmpty() || timeStr.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                double principal = Double.parseDouble(principalStr);
                double rate = Double.parseDouble(rateStr);
                double time = Double.parseDouble(timeStr);

                double simpleInterest = (principal * rate * time) / 100;
                Toast.makeText(MainActivity.this, "Simple Interest: " + simpleInterest, Toast.LENGTH_LONG).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}
