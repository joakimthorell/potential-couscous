package potential_couscous.couscousdrive;

import android.view.View;
import android.widget.Button;

/**
 * Controller for the ACC
 */

public class ACCController implements Interface {
    private Button mACCButton;

    public ACCController(Button button) {
        mACCButton = button;
    }

    @Override
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
