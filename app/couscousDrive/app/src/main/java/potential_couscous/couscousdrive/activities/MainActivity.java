package potential_couscous.couscousdrive.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ToggleGroup;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.controllers.ACCController;
import potential_couscous.couscousdrive.controllers.IFragmentChanger;
import potential_couscous.couscousdrive.controllers.JoystickController;
import potential_couscous.couscousdrive.controllers.MainController;
import potential_couscous.couscousdrive.controllers.PlatoonController;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.view.ACCFragment;
import potential_couscous.couscousdrive.view.JoystickFragment;
import potential_couscous.couscousdrive.view.PlatoonFragment;

/**
 * Main
 */
public class MainActivity extends AppCompatActivity implements IFragmentChanger {
    private static CarCom mCarCom;
    private TextView mSelectMode;

    public static CarCom getCarCom() {
        return mCarCom;
    }

    public static void setCarCom(CarCom carCom) {
        mCarCom = carCom;
        //Setting manual to default
        mCarCom.sendData(mCarCom.MANUAL_KEY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the action bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(toolbar);

        mSelectMode = (TextView) findViewById(R.id.select_mode_textview);


        // Not the best solution... Fix this if there is more time.
        // This solves the networks call on main thread problems
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        //Set Togglebuttons
        ToggleGroup toggleGroup = (ToggleGroup) findViewById(R.id.groupTextAlignment);
        MainController mainController = new MainController();
        mainController.setToggleButtonListener(toggleGroup);

        mainController.setFragmentReplacer(this); //Allow togglebuttons to replace fragments.

        //Set JoystickView
        JoystickFragment joystickFragment = new JoystickFragment();
        JoystickController joystickController = new JoystickController(toggleGroup);
        joystickFragment.setIController(joystickController);
        mainController.setJoystickController(joystickController);

        //Set ACC
        ACCFragment accFragment = new ACCFragment();
        ACCController accController = new ACCController();
        accFragment.setIController(accController);
        mainController.setACCController(accController);

        //Set Platoon
        PlatoonFragment platoonFragment = new PlatoonFragment();
        PlatoonController platoonController = new PlatoonController();
        platoonFragment.setIController(platoonController);
        mainController.setPlatoonController(platoonController);
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
                if (isCarCom()) {
                    Toast.makeText(this, "You are connected...", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(this, ConnectActivity.class);
                    startActivity(i);
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        if (mSelectMode.getVisibility() == View.VISIBLE) {
            mSelectMode.setVisibility(View.INVISIBLE);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_container, fragment);
        ft.commit();
    }

    private boolean isCarCom() {
        return mCarCom != null && mCarCom.isConnected();
    }
}