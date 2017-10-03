package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.Button;

/**
 * Controller for the ACC
 */

public class ACCController {
    private Button mACCButton;

    public ACCController(Button button) {
        mACCButton = button;
    }


    public void setACCListener (Button ACCButton) {
        ACCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //if the button is clicked, accbutton = true... not possible to click another button
                //start acc method...
            }
        });
    }
}
