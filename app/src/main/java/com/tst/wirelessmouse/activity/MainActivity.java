package com.tst.wirelessmouse.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tst.wirelessmouse.R;
import com.tst.wirelessmouse.runnable.TcpThreadRunnable;

public class MainActivity extends AppCompatActivity {
    TcpThreadRunnable tcpThreadRunnable;
    Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
        Button test_sendButton = findViewById(R.id.test_sendBtn);

        tcpThreadRunnable = new TcpThreadRunnable("192.168.2.11", 5000, this);
        thread = new Thread(tcpThreadRunnable);
        thread.start();
        test_sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = ((EditText) findViewById(R.id.editTextView)).getText().toString();
                tcpThreadRunnable.send(text);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tcpThreadRunnable.stop();
    }
}