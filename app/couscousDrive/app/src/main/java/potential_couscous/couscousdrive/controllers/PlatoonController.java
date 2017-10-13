package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.view.IPlatoon;

public class PlatoonController implements IPlatoon {

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
                    if (isCarCom(carCom)) {
                        carCom.sendData(carCom.PLATOON_KEY);
                    }

                } else {
                    rippleBackground.stopRippleAnimation();
                    toggle = !toggle;
                    if (isCarCom(carCom)) {
                        //carCom.sendData(carCom.PLATOON_KEY);

                        //TODO Send message that ends Platoon MODE

                    }
                }
            }
        });
    }

    private boolean isCarCom(CarCom carCom) {
        return carCom != null && carCom.isConnected();
    }

}
