package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.Button;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import potential_couscous.couscousdrive.MainActivity;
import potential_couscous.couscousdrive.utils.AngleCalculator;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.utils.WirelessInoConveret;

public class ManualController {

    public ManualController(JoystickView joystickView, Button manualButton) {
        setmManualButtonListener(manualButton);
        setJoystickViewListener(joystickView);
    }

    private void setmManualButtonListener(Button manualButton) {
        manualButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO
            }
        });
    }

    private void setJoystickViewListener(JoystickView joystickView) {
        joystickView.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                driveCar(angle, strength);
            }
        });
    }

    private void driveCar(int angle, int velocity) {
        int steer = checkData(AngleCalculator.calcAngle(angle));
        int drive =  checkData(AngleCalculator.calcSpeed(angle, velocity));

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