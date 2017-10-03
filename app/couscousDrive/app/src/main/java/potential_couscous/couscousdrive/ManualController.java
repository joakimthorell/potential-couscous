package potential_couscous.couscousdrive;

import android.widget.Button;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import potential_couscous.couscousdrive.utils.AngleCalculator;
import potential_couscous.couscousdrive.utils.WirelessInoConveret;

public class ManualController {
    private Button mManualButton;

    public ManualController(JoystickView joystickView, Button button) {
        mManualButton = button;
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
        //sendData(CarCom.MANUAL_KEY, data);
    }

    private int checkData(int value) {
        return value < -100 || value > 100 ? 0 : value;
    }
}