package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.anastr.speedviewlib.Gauge;
import com.github.anastr.speedviewlib.TubeSpeedometer;
import com.github.anastr.speedviewlib.util.OnSpeedChangeListener;

import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.utils.WirelessInoConveret;
import potential_couscous.couscousdrive.view.IACC;

//TODO Send data to server in all ButtonListeners()

public class ACCController implements IACC {
    private TextView mTextView;
    private int mCurrentVelocity;
    private int mCurrentAngle;
    private CarCom mCarCom;

    public ACCController(CarCom carCom) {
        mCurrentVelocity = 5; //Speedmeter tends to get stuck otherwise
        mCurrentAngle = 100; //Representing steer 0 graphically when right meter is half-way full.
        mCarCom = carCom;
    }

    private void sendNewDrivingValues() {
        String drivingData = WirelessInoConveret.convertData(calcSteeringValue(),
                calcVelocityValue());

        mCarCom.sendData(mCarCom.ACC_KEY, drivingData);
    }

    private int calcSteeringValue() {
        return mCurrentAngle - 100;
    }

    private int calcVelocityValue() {
        return mCurrentVelocity;
    }

    private void setText(String string) {
        if (mTextView != null) {
            mTextView.setText(string);
        }
    } //Currently not used...

    /**
     * Arrow button listeners, updating values for TubeSpeedometer values at the moment.
     *
     * @param leftButton
     */
    private void setLeftButtonListener(ImageButton leftButton) {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentAngle > 0) {
                    mCurrentAngle -= 10;
                    sendNewDrivingValues();
                }
            }
        });
    }

    private void setRightButtonListener(ImageButton rightButton) {
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentAngle < 200) {
                    mCurrentAngle += 10;
                    sendNewDrivingValues();
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
                    sendNewDrivingValues();
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
                    sendNewDrivingValues();
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

    @Override
    public void setACCTextView(TextView textView) {
        mTextView = textView;
    } //Currently not used...

    @Override
    public void setACCTextViewListener() {
        if (mTextView != null) {
            mTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
    } //Currently not used...
}