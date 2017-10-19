package potential_couscous.couscousdrive.controllers;

import android.support.v7.widget.ToggleGroup;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.anastr.speedviewlib.Gauge;
import com.github.anastr.speedviewlib.TubeSpeedometer;
import com.github.anastr.speedviewlib.util.OnSpeedChangeListener;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.utils.JoystickCalculator;
import potential_couscous.couscousdrive.utils.WirelessInoConveret;
import potential_couscous.couscousdrive.view.IJoystick;

public class JoystickController extends AbstractController implements IJoystick {

    private CheckBox mReverseSteering;
    private int mCalibrateSteering;
    private ToggleGroup mToggleGroup;
    private volatile int currentVelocity;
    private TextView mCalibrateDisplay;

    public JoystickController(ToggleGroup toggleGroup) {
        mToggleGroup = toggleGroup;
        currentVelocity = 1;
        mCalibrateSteering = 0;
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
    public void setReverseBox(CheckBox box) {
        mReverseSteering = box;
    }

    @Override
    public void setCalibrateDisplay(TextView textView) {
        mCalibrateDisplay = textView;
        updateCalibrateDisplay();
    }

    private void updateCalibrateDisplay() {
        if (mCalibrateDisplay != null) {
            mCalibrateDisplay.setText(String.valueOf(mCalibrateSteering));
            driveCar(mCalibrateSteering);
        }
    }

    @Override
    public void setCalibrateButtons(ImageButton leftButton, ImageButton rightButton) {
        setCalibreateLeftButton(leftButton);
        setCalibreateRightButton(rightButton);
        updateCalibrateDisplay();
    }

    private void setCalibreateRightButton(ImageButton rightButton) {
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalibrateSteering = mCalibrateSteering < 100 ?
                        mCalibrateSteering + 5 :
                        mCalibrateSteering;
                updateCalibrateDisplay();
            }
        });
    }

    private void setCalibreateLeftButton(ImageButton leftButton) {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCalibrateSteering = mCalibrateSteering > -100 ?
                        mCalibrateSteering - 5 :
                        mCalibrateSteering;
                updateCalibrateDisplay();

            }
        });
    }

    @Override
    public void setTubeSpeedometerListener(TubeSpeedometer velocityMeter) {
        velocityMeter.setSpeedAt(5);//Speedmeter tends to get stuck otherwise
        velocityMeter.setOnSpeedChangeListener(new OnSpeedChangeListener() {
            private int lastVelocity = 100;//Random in order to differ from current
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

    /**
     * This method reformates and sends data from joystick input to server.
     *
     * @param angle
     * @param velocity
     */
    private void driveCar(int angle, int velocity) {
        int steer = checkData(JoystickCalculator.calcAngle(angle) * -1);
        steer = isReverse() ? steer * -1 : steer;
        steer = calculateCalibration(steer);
        int drive = checkData(JoystickCalculator.calcSpeed(angle, velocity));
        String data = WirelessInoConveret.convertData(steer, drive);

        CarCom carCom = CarCom.getCarCom();
        if (isCarCom(carCom)) {
            carCom.sendData(carCom.MANUAL_KEY, data);
        }
    }

    /**
     * This method only send driving value to car. Velocity will be 0
     * @param steeringValue
     */
    private void driveCar(int steeringValue) {
        int steer = checkData(steeringValue);
        steer = isReverse() ? steer * -1 : steer;
        steer = calculateCalibration(steer);

        String data =  WirelessInoConveret.convertData(steer, 0);

        CarCom carCom = CarCom.getCarCom();
        if (isCarCom(carCom)) {
            carCom.sendData(carCom.MANUAL_KEY, data);
        }
    }

    /**
     * This method makes the car turn even. If the wheels are not placed in 0 position with
     * value = 0 then the steering will be as bad in both directions.
     * @param steering original steering value
     * @return calculated value.
     */
    private int calculateCalibration(int steering) {
        if (mCalibrateSteering == 0) {
            return steering;
        }

        return mCalibrateSteering - ((steering * mCalibrateSteering) / 100 );

    }

    private boolean isReverse() {
        if (mReverseSteering != null) {
            return mReverseSteering.isChecked();
        }
        return false;
    }

    private int checkData(int value) {
        return value < -100 || value > 100 ? 0 : value;
    }
}