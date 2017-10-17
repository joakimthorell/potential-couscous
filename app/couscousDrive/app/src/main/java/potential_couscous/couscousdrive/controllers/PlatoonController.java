package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.view.IPlatoon;

public class PlatoonController extends AbstractController implements IPlatoon {
    private ImageView mPlay;
    private ImageView mStop;

    public PlatoonController() {

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
                        carCom.sendData(carCom.PLATOON_KEY);
                    }

                } else {
                    rippleBackground.stopRippleAnimation();
                    toggle = !toggle;

                    if (mPlay != null) {
                        mPlay.setVisibility(View.VISIBLE);
                        mStop.setVisibility(View.INVISIBLE);
                    }

                    if (isCarCom(carCom)) {
                        //carCom.sendData(carCom.PLATOON_KEY, "KILL PLATOON");
                    }
                }
            }
        });
    }

    @Override
    public void setTogglePlatoonImageViews(ImageView playButton, ImageView stopButton) {
        mPlay = playButton;
        mStop = stopButton;
        mStop.setVisibility(View.INVISIBLE); //Set transparent as default
    }
}
