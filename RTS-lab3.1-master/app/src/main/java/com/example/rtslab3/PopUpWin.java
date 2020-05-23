package com.example.rtslab3;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PopUpWin extends AppCompatActivity {


    private TextView textViewT;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_up_window);
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.pop_up_window, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);
        textViewT = (TextView) findViewById(R.id.textViewTime);
        String time = getIntent().getStringExtra("time");
        textViewT.setText(textViewT.getText().toString() + " " + time);
    }

    /*// show the popup window
    // which view you pass in doesn't matter, it is only used for the window tolken
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        *//*TextView time = findViewById(R.id.textViewTime);
        time.setText(String.valueOf(m));*//*

    Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.putExtra("extra", String.valueOf(m));
    startActivity(intent);

    // dismiss the popup window when touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            popupWindow.dismiss();
            return true;
        }
    });*/
}
