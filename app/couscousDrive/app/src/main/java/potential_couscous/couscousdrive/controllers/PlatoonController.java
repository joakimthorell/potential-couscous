package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.utils.WirelessInoConveret;
import potential_couscous.couscousdrive.view.IPlatoon;

public class PlatoonController extends AbstractController implements IPlatoon {
    private ImageView mPlay;
    private ImageView mStop;

    private boolean isDriving;
    private int mSteer;
    private static final int MAX_SPEED = 27;

    public PlatoonController() {
        mSteer = 0;
        isDriving = false;
    }

    @Override
    public void setRippleBackgroundListener(final RippleBackground rippleBackground, ImageView imageView) {
        imageView.setOnClickListener(new View.OnClickListener() {
            private boolean toggle = true;

            @Override
            public void onClick(View view) {
                CarCom carCom = CarCom.getCarCom();

                if (toggle) {
                    rippleBackground.startRippleAnimation();
                    toggle = !toggle;

                    if (mPlay != null) {
                        mStop.setVisibility(View.VISIBLE);
                        mPlay.setVisibility(View.INVISIBLE);
                    }

                    if (isCarCom(carCom)) {
                        carCom.sendData(carCom.PLATOON_KEY, WirelessInoConveret.convertData(mSteer, MAX_SPEED));
                        isDriving = true;
                    }

                } else {
                    rippleBackground.stopRippleAnimation();
                    toggle = !toggle;

                    if (mPlay != null) {
                        mPlay.setVisibility(View.VISIBLE);
                        mStop.setVisibility(View.INVISIBLE);
                    }

                    if (isCarCom(carCom)) {
                        carCom.sendData(carCom.PLATOON_KEY, "V0000");
                        isDriving = false;
                    }
                }
            }
        });
    }

    private void setLeftCalibrationButtonListener(ImageButton left) {
        left.setOnClickListener(new View.OnClickListener() {
            private CarCom carCom = CarCom.getCarCom();

            @Override
            public void onClick(View v) {
                if (mSteer > -100) {
                    mSteer -= 5;
                }
                if (isCarCom(carCom)) {
                    int velocity = isDriving ? MAX_SPEED : 0;
                    carCom.sendData(carCom.PLATOON_KEY, WirelessInoConveret.convertData(mSteer, velocity));
                }
            }
        });

    }

    private void setRightCalibrationButtonListener(ImageButton right) {
        right.setOnClickListener(new View.OnClickListener() {
            private CarCom carCom = CarCom.getCarCom();

            @Override
            public void onClick(View v) {
                if (mSteer < 100) {
                    mSteer += 5;
                }
                if (isCarCom(carCom)) {
                    int velocity = isDriving ? MAX_SPEED : 0;
                    carCom.sendData(carCom.PLATOON_KEY, WirelessInoConveret.convertData(mSteer, velocity));
                }
            }
        });

    }

    @Override
    public void setPlatoonButtonListeners(ImageButton left, ImageButton right) {
        setLeftCalibrationButtonListener(left);
        setRightCalibrationButtonListener(right);
    }

    @Override
    public void setTogglePlatoonImageViews(ImageView playButton, ImageView stopButton) {
        mPlay = playButton;
        mStop = stopButton;
        mStop.setVisibility(View.INVISIBLE); //Set transparent as default
    }
}
