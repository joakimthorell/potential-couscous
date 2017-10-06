package potential_couscous.couscousdrive.controllers;

import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.widget.ToggleGroup;

import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.activities.MainActivity;
import potential_couscous.couscousdrive.utils.CarCom;

public class MainController {
    public MainController(ToggleGroup toggleGroup) {
        setToggleButtonListener(toggleGroup);
    }

    public void setToggleButtonListener(final ToggleGroup toggleGroup) {
        toggleGroup.setOnCheckedChangeListener(new ToggleGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ToggleGroup group, @IdRes int[] checkedId) {
                CarCom carCom = MainActivity.getCarCom();
                switch (toggleGroup.getCheckedId()) {
                    case (R.id.manual_button):
                        if (checkCarCom(carCom)) {
                            carCom.sendData(carCom.MANUAL_KEY);

                        }
                        break;
                    case (R.id.acc_button):
                        if (checkCarCom(carCom)) {
                            carCom.sendData(carCom.ACC_KEY);
                        }
                        break;
                    case (R.id.platoon_button):
                        if (checkCarCom(carCom)) {
                            carCom.sendData(carCom.PLATOON_KEY);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private boolean checkCarCom(CarCom carCom) {
        return carCom != null && carCom.isConnected();
    }
}