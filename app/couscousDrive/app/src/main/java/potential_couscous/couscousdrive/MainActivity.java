/**
 *  Main activity for couscousDRIVE application.
 *  As of version 1.0 the application is not getting
 *  any data back from the socket. This app only sends data for now.
 *
 *  Buttons:
 *  There are for now 2 buttons.
 *  <b>LockButton</b> is making the joystick "stiff" and wont move back to origo
 *  when you release you finger from display.
 *  <b>ACCButton</b> is coming later.
 *
 *  @IMPORTANT:
 *  The data coming from app is xxx:yyy where x is steering and y speed.
 *
 *  @Version: 1.0
 */
package potential_couscous.couscousdrive;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public class MainActivity extends AppCompatActivity {

    private static Socket mSocket;
    private static PrintWriter out;

    private boolean isConnected;
    private JoystickView mJoystick;
    private Toolbar mToolbar;
    private Button mLockButton;
    private Button mAcc_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Not the best solution... If there is more time, fix this.
        // This is solving the networks call on main thread problems
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        // Checks if socket is setup.
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

        mLockButton = (Button) findViewById(R.id.lock_button);
        mAcc_button = (Button) findViewById(R.id.acc_button);

        mLockButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mJoystick.isAutoReCenterButton()) {
                    mJoystick.setAutoReCenterButton(true);
                    Toast.makeText(MainActivity.this, "Steering is manual", Toast.LENGTH_SHORT).show();
                } else {
                    mJoystick.setAutoReCenterButton(false);
                    Toast.makeText(MainActivity.this, "Steering is locked", Toast.LENGTH_SHORT).show();
                }
            }
        });


        mAcc_button.setText("TestButton");
        mAcc_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Using this button as test button for now.
                sendData("test");
            }
        });

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
                    Toast.makeText(this, "You are connected", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(this, ConnectActivity.class);
                    startActivity(i);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void driveCar(int angle, int str) {
        int mopedSteeringValue = AngleCalculator.calcAngle(angle);

        sendDrivingToMoped(mopedSteeringValue, str);
    }

    /**
     * Sending data to socket as String. This method converts the values
     * to readable Strings as the MOPED knows how to decrypt.
     *
     * On MOPED read string as xxx:yyy where xxx is steering and yyy speed.
     * Always divided by : (colon)
     *
     * @param steeringValue int value that MOPED can accept. Between -100 to 100
     * @param speedValue int value that MOPED can accept. Between -100 to 100
     */
    public void sendDrivingToMoped(int steeringValue, int speedValue) {
        // making sure both ints not outside of definition value
        steeringValue = steeringValue < -100 || steeringValue > 100 ? 0 : steeringValue;
        speedValue = speedValue < -100 || speedValue > 100 ? 0 : speedValue;



        String data = steeringValue + ":" + speedValue;

        sendData(data);

    }


    // Sending string to socket.
    private void sendData(String data) {
        if (out != null) {
            out.println(data);
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
