package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.anastr.speedviewlib.Gauge;
import com.github.anastr.speedviewlib.TubeSpeedometer;
import com.github.anastr.speedviewlib.util.OnSpeedChangeListener;

import potential_couscous.couscousdrive.view.IACC;

public class ACCController implements IACC {
    private TextView mTextView;
    private int mCurrentVelocity;
    private int mCurrentAngle;

    public ACCController() {
        mCurrentVelocity = 5; //Speedmeter tends to get stuck otherwise
        mCurrentAngle = 100;
    }
    private void setText(String string) {
        if (mTextView != null) {
            mTextView.setText(string);
        }
    } //Currently not used...

    private void setLeftButtonListener(ImageButton leftButton) {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentAngle > 0) {
                    mCurrentAngle -= 10;
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
                }
            }
        });
    }

    private void setAngleMeterListener(TubeSpeedometer angleMeter) {
        angleMeter.setSpeedAt(100);
        angleMeter.setOnSpeedChangeListener(new OnSpeedChangeListener() {
            private int lastAngle = 100;

            @Override
            public void onSpeedChange(Gauge gauge, boolean isSpeedUp, boolean isByTremble) {
                if (mCurrentAngle != lastAngle) {
                    gauge.speedTo(mCurrentAngle, 900);
                    lastAngle = mCurrentAngle;
                }
            }
        });
    }

    private void setVelocityMeterListener(final TubeSpeedometer velocityMeter) {
        velocityMeter.speedTo(mCurrentVelocity);
        velocityMeter.setOnSpeedChangeListener(new OnSpeedChangeListener() {
            private int lastVelocity = 100;

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
    public void setACCTubeSpeedmeterListeners(TubeSpeedometer velocityMeter, TubeSpeedometer angleMeter) {
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