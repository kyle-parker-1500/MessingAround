package com.example.messingaround;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button textAddButton;
    TextView textDisplayTextView;
    EditText getInputEditText;
    String userInputText;
    public static final String TAG = "MAIN ACTIVITY :: ";


    @Override
    protected void onCreate(Bundle steven) {
        super.onCreate(steven);
        setContentView(R.layout.activity_main);

        textAddButton = (Button) findViewById(R.id.textAddButton);
        textDisplayTextView = (TextView) findViewById(R.id.textDisplayTextView);
        getInputEditText = (EditText) findViewById(R.id.buttonEditText);
        textAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userInputText = getInputEditText.getText().toString();
                textDisplayTextView.setText(userInputText);
            }
        });

        textAddButton.setOnLongClickListener(new View.OnLongClickListener() {
            boolean isVisible = true;
            @Override
            public boolean onLongClick(View v) {
                // todo: - it's not showing back up
                isVisible = !isVisible;
                if (!isVisible)
                    textDisplayTextView.setVisibility(View.INVISIBLE);
                return false;
            }
        });
    }
}