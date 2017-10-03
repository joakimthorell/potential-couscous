package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.Button;

/**
 * Controller for Platooning
 */

public class PlatoonController {
    private Button mPlatoonButton;

    public PlatoonController(Button button) {
        mPlatoonButton = button;
    }

    public void setPlatooningListener(Button platoonButton) {
        platoonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //start platooning method or something like that...

            }
        });
    }
}
