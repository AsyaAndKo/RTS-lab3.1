package com.example.rtslab3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void getFactors(View view) {
            EditText editTextNumber = findViewById(R.id.number);

            String inputNumber = editTextNumber.getText().toString();

            if (inputNumber.isEmpty()) {
                Toast.makeText(this, "Number can't be empty!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            final int number = Integer.parseInt(inputNumber);
            if (isPrime(number)) {
                Toast.makeText(this, "Number should not be prime!",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (number % 2 == 0) {
                Toast.makeText(this, "Number should not be even!",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            TextView textViewA = findViewById(R.id.lab31A);
            TextView textViewB = findViewById(R.id.lab31B);
            final int[][] parts = new int[1][1];

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            Future<?> future = executorService.submit(new Runnable() {
                @Override
                public void run() {
                    parts[0] = MainActivity.this.performFactorization(number);
                }
            });
            executorService.shutdown();


                textViewA.setText("A = " + parts[0][0]);
                textViewB.setText("B = " + parts[0][1]);


            try {
                if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
            }

        }

        private int[] performFactorization(int n) {
            int[] result = new int[2];
            int k = 0;
            int x = (int) Math.ceil((Math.sqrt(n) + k));
            double y = Math.sqrt(x * x - n);

            while (y % 1 != 0) {
                k++;
                x = (int) (Math.sqrt(n) + k);
                y = Math.sqrt(x * x - n);
            }
            result[0] = (int) (x + y);
            result[1] = (int) (x - y);
            return result;
        }

        private boolean isPrime(int n) {
            for (int i = 2; i <= Math.sqrt(n); i++) {
                if (n % i == 0) {
                    return false;
                }
            }
            return true;
    }
}
