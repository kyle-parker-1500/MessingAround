package com.example.ctofconverter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ctofconverter.Utils.Converters;
import com.example.ctofconverter.databinding.ActivityFtoCactivityBinding;
import com.example.ctofconverter.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String CONVERTED_VALUE_EXTRA_KEY = "MainActivity_Converted_Value_double";

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.CtoFTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "It worked!", Toast.LENGTH_SHORT).show();
            }
        });

        binding.celsiusConvertButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = FtoCActivity.fToCIntentFactory(getApplicationContext(), convertValue());
                startActivity(intent);
                return false;
            }
        });
    }

    private double convertValue() {
        String enteredValue = binding.celsiusValueEditText.getText().toString();

        double valueToConvert = 0;
        if (!enteredValue.isEmpty()) {
            valueToConvert = Double.parseDouble(enteredValue);
        }
        valueToConvert = Converters.celsiusToFahrenheit(valueToConvert);

        return valueToConvert;
    }

    public void displayConvertedValue(View view) {
        binding.celsiusConvertedValueTextView.setText(
                getString(R.string.degrees_fahrenheit, convertValue())
        );
    }

    public static Intent MainActivityIntentFactory(Context context, double recievedValue) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(CONVERTED_VALUE_EXTRA_KEY, recievedValue);
        return intent;
    }
}