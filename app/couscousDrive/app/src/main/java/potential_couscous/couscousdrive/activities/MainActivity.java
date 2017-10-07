/**
 * Main
 */
package potential_couscous.couscousdrive.activities;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ToggleGroup;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import potential_couscous.couscousdrive.controllers.ACCController;
import potential_couscous.couscousdrive.controllers.JoystickController;
import potential_couscous.couscousdrive.view.ACCFragment;
import potential_couscous.couscousdrive.view.IFragmentChanger;
import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.controllers.MainController;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.view.JoystickFragment;

public class MainActivity extends AppCompatActivity implements IFragmentChanger {
    private static CarCom mCarCom;
    private Toolbar mToolbar;
    private TextView mSelectMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setup the action bar
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);

        mSelectMode = (TextView) findViewById(R.id.select_mode_textview);

        /*
        // Not the best solution... Fix this if there is more time.
        // This solves the networks call on main thread problems
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        */

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
        mainController.setmACCController(accController);

        /*
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_container, joystickFragment);
        ft.commit();
        */
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

    @Override
    public void replaceFragment(Fragment fragment) {
        if (mSelectMode.getVisibility() == View.VISIBLE) {
            mSelectMode.setVisibility(View.INVISIBLE);
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.main_fragment_container, fragment);
        ft.commit();
    }

    public static void setCarCom(CarCom carCom) {
        mCarCom = carCom;
        //Setting manual to default
        mCarCom.sendData(mCarCom.mMANUAL_KEY);
    }

    public static CarCom getCarCom() {
        return mCarCom;
    }
}