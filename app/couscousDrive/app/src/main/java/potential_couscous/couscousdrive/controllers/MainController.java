package potential_couscous.couscousdrive.controllers;

import android.support.v4.app.Fragment;
import android.support.annotation.IdRes;
import android.support.v7.widget.ToggleGroup;
import android.widget.TextView;

import potential_couscous.couscousdrive.view.ACCFragment;
import potential_couscous.couscousdrive.view.FragmentFactory;
import potential_couscous.couscousdrive.view.IFragmentChanger;
import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.activities.MainActivity;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.view.JoystickFragment;

public class MainController {
    private IFragmentChanger mFragmentReplacer;
    private JoystickController mJoystickController;
    private ACCController mACCController;

    public MainController() {
    }

    public void setToggleButtonListener(final ToggleGroup toggleGroup) {
        toggleGroup.setOnCheckedChangeListener(new ToggleGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ToggleGroup group, @IdRes int[] checkedId) {
                CarCom carCom = MainActivity.getCarCom();
                switch (toggleGroup.getCheckedId()) {
                    case (R.id.manual_button):
                        replaceFragment(FragmentFactory.createJoystickFragment(mJoystickController));

                        if (isCarCom(carCom)) {
                            carCom.sendData(carCom.mMANUAL_KEY);
                        }
                        break;
                    case (R.id.acc_button):
                        replaceFragment(FragmentFactory.createACCFragment(mACCController));

                        if (isCarCom(carCom)) {
                            carCom.sendData(carCom.mACC_KEY);
                        }
                        break;
                    case (R.id.platoon_button):
                        if (isCarCom(carCom)) {
                            carCom.sendData(carCom.mPLATOON_KEY);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        if (mFragmentReplacer != null) {
            mFragmentReplacer.replaceFragment(fragment);
        }
    }

    private boolean isCarCom(CarCom carCom) {
        return carCom != null && carCom.isConnected();
    }

    public void setFragmentReplacer(IFragmentChanger fragmentReplacer) {
        mFragmentReplacer = fragmentReplacer;
    }

    public void setJoystickController(JoystickController joystickController) {
        mJoystickController = joystickController;
    }

    public void setmACCController(ACCController ACCController) {
        mACCController = ACCController;
    }
}