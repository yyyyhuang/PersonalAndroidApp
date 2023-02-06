package edu.northeastern.numad23sp_yingjiehuang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.os.Handler;
import android.annotation.SuppressLint;

public class PrimeActivity extends AppCompatActivity {

    private Handler textHandler = new Handler();
    TextView current, latest_prime;
    Button find, terminate;
    differentThread t;

    int start = 3;
    int current_prime = 2;
    private volatile boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime);

        running = false;
        current = findViewById(R.id.number);
        latest_prime = findViewById(R.id.prime);
        find = findViewById(R.id.find);
        terminate = findViewById(R.id.terminate);


        // rotation continues search
        if(savedInstanceState != null) {
            start = savedInstanceState.getInt("start");
            current.setText("current number being check is " + start);
            current_prime = savedInstanceState.getInt("current_prime");
            latest_prime.setText("latest prime is " + current_prime);
        }

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(running) return;
                running = true;
                t = new differentThread(start);
                t.start();
            }
        });

        terminate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!running) return;
                running = false;
                start = 3;
                current_prime = 2;
                t.interrupt();
            }
        });

    }

    // save outstate
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("start", start);
        outState.putInt("current_prime", current_prime);
    }

    
    class differentThread extends Thread {
        int c;

        differentThread(int start) {
            this.c = start;
        }

        @Override
        public void run() {
            for (int i = c; i < 1000000; i += 2) {
                final int finalI = i;
                textHandler.post(new Runnable() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void run() {
                        if (finalI > 1000){
                            if (finalI % 100 == 0){
                                current.setText("current number being check is " + finalI);
                                start = finalI;
                            }
                        } else {
                            current.setText("current number being check is " + finalI);
                            start = finalI;
                        }
                        boolean isPrime = true;
                        for (int j=2; j <= finalI / 2; j++){
                            if(finalI % j == 0){
                                isPrime = false;
                                break;
                            }
                        }
                        if(isPrime){
                            latest_prime.setText("latest prime is " + finalI);
                            current_prime = finalI;
                        }
                    }
                });
                try{
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }

        }
    }








}