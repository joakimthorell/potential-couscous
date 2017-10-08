package potential_couscous.couscousdrive.controllers;

import android.support.v7.widget.ToggleGroup;

import com.github.anastr.speedviewlib.Gauge;
import com.github.anastr.speedviewlib.TubeSpeedometer;
import com.github.anastr.speedviewlib.util.OnSpeedChangeListener;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.activities.MainActivity;
import potential_couscous.couscousdrive.utils.JoystickCalculator;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.utils.WirelessInoConveret;

public class JoystickController implements IJoystick {
    private ToggleGroup mToggleGroup;
    private volatile int currentVelocity;

    public JoystickController(ToggleGroup toggleGroup) {
        mToggleGroup = toggleGroup;
        currentVelocity = 1;
    }

    @Override
    public void setJoystickViewListener(JoystickView joystickView) {
        joystickView.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                if (mToggleGroup.getCheckedId() == R.id.manual_button) {
                    currentVelocity = strength;
                    driveCar(angle, strength);
                }
            }
        });
    }

    @Override
    public void setTubeSpeedometerListener(TubeSpeedometer velocityMeter) {
        velocityMeter.setSpeedAt(5);
        velocityMeter.setOnSpeedChangeListener(new OnSpeedChangeListener() {
            private int lastVelocity = 100;
            private boolean once = true;
            @Override
            public void onSpeedChange(Gauge gauge, boolean isSpeedUp, boolean isByTremble) {
                if (currentVelocity == 0 && once) {
                    gauge.realSpeedTo(5);
                    once = false;
                } else if (currentVelocity != lastVelocity && currentVelocity > 0) {
                    gauge.speedTo(currentVelocity, 900);
                    lastVelocity = currentVelocity;
                    once = true;
                }
            }
        });
    }

    private void driveCar(int angle, int velocity) {
        int steer = checkData(JoystickCalculator.calcAngle(angle) * -1); // multiply with -1 to reverse steering
        int drive = checkData(JoystickCalculator.calcSpeed(angle, velocity));
        String data = WirelessInoConveret.convertData(steer, drive);

        CarCom carCom = MainActivity.getCarCom();
        if (carCom != null && carCom.isConnected()) {
            carCom.sendData(carCom.mMANUAL_KEY, data);
        }
    }

    private int checkData(int value) {
        return value < -100 || value > 100 ? 0 : value;
    }
}