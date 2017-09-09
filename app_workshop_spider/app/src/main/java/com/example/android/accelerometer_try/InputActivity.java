package com.example.android.accelerometer_try;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.github.nkzawa.socketio.client.IO;

import java.net.URISyntaxException;

public class InputActivity extends AppCompatActivity {
    Button button;
    static EditText ip;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);


        button = (Button) findViewById(R.id.button);
        ip = (EditText) findViewById(R.id.ip_add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                {


                        if (TextUtils.isEmpty(ip.getText().toString())) {
                            ip.setError("Please Enter Item");
                            ip.requestFocus();
                            return;
                        } else {
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(i);


                    }
                }
            }
        });


    }
}
