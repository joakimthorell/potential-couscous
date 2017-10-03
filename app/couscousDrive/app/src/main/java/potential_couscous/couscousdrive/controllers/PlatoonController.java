package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.Button;

import potential_couscous.couscousdrive.activities.MainActivity;
import potential_couscous.couscousdrive.utils.CarCom;

public class PlatoonController {

    public PlatoonController(Button platoonButton) {
        setPlatoonButtonListener(platoonButton);
    }

    public void setPlatoonButtonListener(Button platoonButton) {
        platoonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarCom carCom = MainActivity.getCarCom();

                if (carCom != null && carCom.isConnected()) {
                    carCom.sendData(carCom.MANUAL_KEY, null);
                }
            }
        });
    }
}
