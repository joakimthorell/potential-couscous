package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.Button;

import potential_couscous.couscousdrive.utils.CarCom;

/**
 * Controller for Platooning
 */

public class PlatoonController {
    private Button mPlatoonButton;
    private final String PLATOON_KEY;

    public PlatoonController(Button button, String string) {
        mPlatoonButton = button;
        PLATOON_KEY = string;
    }

    public void setPlatooningListener(Button platoonButton) {
        platoonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send info to server
               //sendData(PLATOON_KEY);

            }
        });
    }
}
