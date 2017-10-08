package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

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
                if (toggle) {
                    rippleBackground.startRippleAnimation();
                    toggle = !toggle;
                } else {
                    rippleBackground.stopRippleAnimation();
                    toggle = !toggle;
                }
            }
        });
    }
}
