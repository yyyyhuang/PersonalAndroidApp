package edu.northeastern.numad23sp_yingjiehuang;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    private static final String TAG = "DetailActivity___";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    public void onClick(View view) {
        TextView change = findViewById(R.id.textView);
        int theId = view.getId();
        if (theId == R.id.button_A) {
            change.setText("Pressed: A");
        } else if (theId == R.id.button_B) {
            change.setText("Pressed: B");
        } else if (theId == R.id.button_C) {
            change.setText("Pressed: C");
        } else if (theId == R.id.button_D) {
            change.setText("Pressed: D");
        } else if (theId == R.id.button_E) {
            change.setText("Pressed: E");
        } else if (theId == R.id.button_F) {
            change.setText("Pressed: F");
        }
    }
}