package edu.northeastern.numad23sp_yingjiehuang;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = (Button) findViewById(R.id.button_send);
        button2 = (Button) findViewById(R.id.clicky);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Yingjie Huang, huang.yingj@northeastern.edu", Toast.LENGTH_LONG).show();//display the text of button
                // startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Toast.makeText(getApplicationContext(), "Yingjie Huang, huang.yingj@northeastern.edu", Toast.LENGTH_LONG).show();//display the text of button
                startActivity(new Intent(MainActivity.this, DetailActivity.class));
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, LinksActivity.class));
            }
        });


    }




}