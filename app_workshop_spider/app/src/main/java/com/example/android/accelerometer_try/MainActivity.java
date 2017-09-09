package com.example.android.accelerometer_try;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;

import java.net.URISyntaxException;


public class MainActivity extends Activity implements SensorEventListener {

    int a=0;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    TextView tv1,tv2,tv3;


    private Socket mSocket;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        tv1 = (TextView) findViewById(R.id.tv1);

//        mSocket.on("connect", new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                Log.d("Connect","Connect");
//            }
//        });
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);//for getting access to hardware sensor

        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


                {
                    try {
                            mSocket = IO.socket("http://" + InputActivity.ip.getText().toString());
                            mSocket.connect();
                            InputActivity.ip.setText("");

                    } catch (URISyntaxException e) {
                        Log.d("xyz", "xyz");
                    }
                }
            }




    private void attemptSend(String message) {

        mSocket.emit("clientEvent", message);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mSocket.disconnect();
        //mSocket.off("new message", onNewMessage);
    }

    protected void onResume() {

        super.onResume();

        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

    }


    protected void onPause() {

        super.onPause();
        mSensorManager.unregisterListener(this);

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }


    public void onSensorChanged(final SensorEvent event) {
        Sensor mySensor = event.sensor;
        double x=0.0;
        if (mySensor.getType() == Sensor.TYPE_ACCELEROMETER) {
           x = event.values[0];

            attemptSend(String.valueOf(x));
            tv1.setText(String.valueOf(x));
        }






    }


}









