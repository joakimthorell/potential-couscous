package potential_couscous.couscousdrive;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {

    private static Socket mSocket;
    private static PrintWriter out;

    private JoystickView mJoystick;
    private Toolbar mToolbar;
    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Not the best solution... If there is more time, fix this.
        // This is solving the networks call on main thread problems
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        isConnected = mSocket != null && mSocket.isConnected();

        // Setting up The joystick
        mJoystick = (JoystickView) findViewById(R.id.joystick);
        mJoystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                System.out.println("Angle: " + angle + ".......... Strength: " + strength);
                driveCar(angle, strength);
            }
        });

        // Setup the action bar
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.topmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_connect:
                if (isConnected) {
                    // Disconnect instead and show some msg about it
                } else {
                    Intent i = new Intent(this, ConnectActivity.class);
                    startActivity(i);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private int calcSteering(int joystickAngle) {
        // if its less then 180 we dont need to do anything
        if (joystickAngle < 180) {
            return joystickAngle;
        }
        if (joystickAngle >= 270) {
            return 360 - joystickAngle;
        }
        if (joystickAngle >= 180) {
            return joystickAngle - 90;
        }

        return 90;
    }

    private void driveCar(int angle, int str) {
        int nAngle = calcSteering(angle);
        str = angle > 180 ? str * -1 : str;
        String direction;
        String speed;
        if (nAngle > 90) {
            direction = "Left";
        } else {
            direction = "Right";
        }

        speed = String.valueOf(str);
        String[] arr = new String[2];
        arr[0] = direction;
        arr[1] = speed;

        sendData(arr);
        // Now we need to send this data to the car.

    }

    /**
     * This method can be much more pretty.
     * Fornow it takes a String array of length 2 and sends index 0 and 1
     * @param data String array with atleast length 2.
     */
    private void sendData(String[] data) {
        if (out != null) {
            out.println(data[0] + ":" + data[1]);
        }
    }

    private static void init() {
        try {
            out = new PrintWriter
                    (new BufferedWriter
                            (new OutputStreamWriter
                                    (mSocket.getOutputStream())), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setSocket(Socket socket) {
        mSocket = socket;
        init();
    }

}
