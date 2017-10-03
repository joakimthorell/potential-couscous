package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.Button;

import potential_couscous.couscousdrive.utils.CarCom;

/**
 * Controller for the ACC
 */

public class ACCController {
    private Button mACCButton;
    private final String ACC_KEY;

    public ACCController(Button button, String string) {
        mACCButton = button;
        ACC_KEY = string;
    }


    public void setACCListener (Button ACCButton) {
        ACCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send info to server
                //sendData(ACC_KEY);

            }
        });
    }
}
