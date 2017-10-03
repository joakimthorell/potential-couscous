/**
 * Main activity for couscousDRIVE application.
 * As of version 1.0 the application is not getting
 * any data back from the socket. This app only sends data for now.
 * <p>
 * Buttons:
 * There are for now 2 buttons.
 * <b>LockButton</b> is making the joystick "stiff" and wont move back to origo
 * when you release you finger from display.
 * <b>ACCButton</b> is coming later.
 *
 * @IMPORTANT: The data coming from app is xxx:yyy where x is steering and y speed.
 * @Version: 1.0
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
import android.widget.Button;
import android.widget.Toast;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import potential_couscous.couscousdrive.controllers.ACCController;
import potential_couscous.couscousdrive.controllers.ManualController;
import potential_couscous.couscousdrive.controllers.PlatoonController;
import potential_couscous.couscousdrive.utils.CarCom;

public class MainActivity extends AppCompatActivity {
    private static CarCom mCarCom;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the action bar
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        //TODO Behövs detta ?
        /*
        // Not the best solution... Fix this if there is more time.
        // This is solves the networks call on main thread problems
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        */

        // Setting up The joystick
        JoystickView joystickView = (JoystickView) findViewById(R.id.joystick);
        Button manualButton = (Button) findViewById(R.id.manual_button);
        new ManualController(joystickView, manualButton);

        // Setting up ACC button
        Button ACCButton = (Button) findViewById(R.id.acc_button);
        new ACCController(ACCButton);

        // Setting upp platoon button
        Button platoonButton = (Button) findViewById(R.id.platoon_button);
        new PlatoonController(platoonButton);
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
                if (mCarCom != null && mCarCom.isConnected()) {
                    Toast.makeText(this, "You are connected...", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(this, ConnectActivity.class);
                    startActivity(i);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public static void setCarCom(CarCom carCom) {
        mCarCom = carCom;
    }

    public static CarCom getCarCom() {
        return mCarCom;
    }
}