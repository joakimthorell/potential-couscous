package potential_couscous.couscousdrive.controllers;

import android.os.Build;
import android.support.annotation.IdRes;
import android.support.v7.widget.ToggleGroup;
import android.view.View;

import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.activities.MainActivity;
import potential_couscous.couscousdrive.utils.CarCom;

public class MainController {
    public MainController(ToggleGroup toggleGroup) {
        setToggleButtonListener(toggleGroup);
    }

    public void setToggleButtonListener(final ToggleGroup toggleGroup) {
            toggleGroup.setOnCheckedChangeListener(new ToggleGroup.OnCheckedChangeListener() {
                CarCom carCom = MainActivity.getCarCom();
                @Override
                public void onCheckedChanged(ToggleGroup group, @IdRes int[] checkedId) {
                    switch (toggleGroup.getCheckedId()) {
                        case (R.id.manual_button):
                            carCom.sendData(carCom.MANUAL_KEY);
                            System.out.println("Manuell");
                            break;
                        case(R.id.acc_button):
                            carCom.sendData(carCom.ACC_KEY);
                            System.out.println("ACC");
                            break;
                        case(R.id.platoon_button):
                            carCom.sendData(carCom.PLATOON_KEY);
                            System.out.println("Kolonn");
                            break;
                        default:
                            break;
                }
            }
        });
    }
}
