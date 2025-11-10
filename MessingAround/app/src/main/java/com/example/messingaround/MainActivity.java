package com.example.messingaround;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button descriptionChangeButton;

    @Override
    protected void onCreate(Bundle steven) {
        super.onCreate(steven);
        setContentView(R.layout.activity_main);

        descriptionChangeButton = (Button) findViewById(R.id.changeDescriptionButton);
        descriptionChangeButton.setOnClickListener(new View.OnClickListener() {
            TextView descriptionTextView = (TextView) findViewById(R.id.changeDescriptionButton);
            boolean isVisible = false;
            @Override
            public void onClick(View v) {
                isVisible = !isVisible;
                if (!isVisible)
                    descriptionTextView.setVisibility(View.INVISIBLE);
            }
        });
    }
}