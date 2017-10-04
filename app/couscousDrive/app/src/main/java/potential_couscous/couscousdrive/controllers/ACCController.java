package potential_couscous.couscousdrive.controllers;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.widget.Button;

import potential_couscous.couscousdrive.activities.MainActivity;
import potential_couscous.couscousdrive.utils.CarCom;

public class ACCController {

    public ACCController(Button ACCButton) {
        setACCListener(ACCButton);
    }

    public void setACCListener (final Button ACCButton) {
        ACCButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CarCom carCom = MainActivity.getCarCom();

                if (carCom != null && carCom.isConnected()) {
                    ACCButton.setBackgroundColor(Color.GREEN);
                    ACCButton.setTextColor(Color.BLACK);
                    carCom.sendData(carCom.ACC_KEY, null);
                }
            }
        });
    }
}
