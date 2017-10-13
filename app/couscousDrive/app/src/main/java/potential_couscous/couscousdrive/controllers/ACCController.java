package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.anastr.speedviewlib.Gauge;
import com.github.anastr.speedviewlib.TubeSpeedometer;
import com.github.anastr.speedviewlib.util.OnSpeedChangeListener;

import potential_couscous.couscousdrive.activities.MainActivity;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.utils.WirelessInoConveret;
import potential_couscous.couscousdrive.view.IACC;

//TODO Send data to server in all ButtonListeners()

public class ACCController implements IACC {
    private int mCurrentVelocity;
    private int mCurrentAngle;

    public ACCController() {
        mCurrentVelocity = 5; //Speedmeter tends to get stuck otherwise
        mCurrentAngle = 100; //Representing steer 0 graphically when right meter is half-way full.
    }

    private void sendData() {
        String data = WirelessInoConveret.convertData(calcSteerValue(),
                calcDriveValue());

        CarCom carCom = CarCom.getCarCom();
        if (carCom != null && carCom.isConnected()) {
            carCom.sendData(carCom.ACC_KEY, data);
        }
    }

    private int calcSteerValue() {
        return mCurrentAngle - 100;
    }

    private int calcDriveValue() {
        return mCurrentVelocity;
    }

    /**
     * Arrow button listeners, updating values for TubeSpeedometer values at the moment.
     *
     * @param leftButton
     */
    private void setRightButtonListener(ImageButton leftButton) {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentAngle > 0) {
                    mCurrentAngle -= 10;
                    sendData();
                }
            }
        });
    }

    private void setLeftButtonListener(ImageButton rightButton) {
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentAngle < 200) {
                    mCurrentAngle += 10;
                    sendData();
                }
            }
        });
    }

    private void setUpButtonListener(ImageButton upButton) {
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentVelocity < 100) {
                    mCurrentVelocity += 5;
                    sendData();
                }
            }
        });
    }

    private void setDownButtonListener(ImageButton downButton) {
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentVelocity > 0) {
                    mCurrentVelocity -= 5;
                    sendData();
                }
            }
        });
    }


    /**
     * Right anglemeter, set on default 100 representing steering 0.
     *
     * @param angleMeter
     */
    private void setAngleMeterListener(TubeSpeedometer angleMeter) {
        angleMeter.setSpeedAt(100);
        angleMeter.setOnSpeedChangeListener(new OnSpeedChangeListener() {
            private int lastAngle = 100; //Random number in order not to match  mCurrentAngle.

            @Override
            public void onSpeedChange(Gauge gauge, boolean isSpeedUp, boolean isByTremble) {
                if (mCurrentAngle != lastAngle) {
                    gauge.speedTo(mCurrentAngle, 900);
                    lastAngle = mCurrentAngle;
                }
            }
        });
    }

    /**
     * Left velocitymeter, set on default 5 as it tends to get stuck on 0 otherwise.
     *
     * @param velocityMeter
     */
    private void setVelocityMeterListener(final TubeSpeedometer velocityMeter) {
        velocityMeter.speedTo(mCurrentVelocity);
        velocityMeter.setOnSpeedChangeListener(new OnSpeedChangeListener() {
            private int lastVelocity = 100; //Random number in order not to match  mCurrentVelocity.

            @Override
            public void onSpeedChange(Gauge gauge, boolean isSpeedUp, boolean isByTremble) {
                if (mCurrentVelocity != lastVelocity) {
                    gauge.speedTo(mCurrentVelocity, 900);
                    lastVelocity = mCurrentVelocity;
                }
            }
        });
    }

    @Override
    public void setACCImageButtonListeners(ImageButton left, ImageButton right, ImageButton up, ImageButton down) {
        setLeftButtonListener(left);
        setRightButtonListener(right);
        setUpButtonListener(up);
        setDownButtonListener(down);
    }

    @Override
    public void setACCTubeSpeedometerListeners(TubeSpeedometer velocityMeter, TubeSpeedometer angleMeter) {
        setVelocityMeterListener(velocityMeter);
        setAngleMeterListener(angleMeter);
    }
}