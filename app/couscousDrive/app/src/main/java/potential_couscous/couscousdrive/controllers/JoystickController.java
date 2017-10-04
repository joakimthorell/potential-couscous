package potential_couscous.couscousdrive.controllers;

import android.support.v7.widget.ToggleGroup;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.activities.MainActivity;
import potential_couscous.couscousdrive.utils.AngleCalculator;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.utils.WirelessInoConveret;

public class JoystickController {

    public JoystickController(JoystickView joystickView, ToggleGroup toggleGroup) {
        setJoystickViewListener(joystickView, toggleGroup);
    }

    private void setJoystickViewListener(final JoystickView joystickView, final ToggleGroup toggleGroup) {
        joystickView.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                if (toggleGroup.getCheckedId()== R.id.manual_button) {
                    driveCar(angle, strength);
                }
            }
        });
    }

    private void driveCar(int angle, int velocity) {
        int steer = checkData(AngleCalculator.calcAngle(angle));
        int drive = checkData(AngleCalculator.calcSpeed(angle, velocity));
        String data = WirelessInoConveret.convertData(steer, drive);

        CarCom carCom = MainActivity.getCarCom();
        if (carCom != null && carCom.isConnected()) {
            carCom.sendData(carCom.MANUAL_KEY, data);
        }
    }

    private int checkData(int value) {
        return value < -100 || value > 100 ? 0 : value;
    }
}