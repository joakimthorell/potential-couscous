package potential_couscous.couscousdrive.controllers;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v7.widget.ToggleGroup;

import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.activities.MainActivity;
import potential_couscous.couscousdrive.utils.CarCom;
import potential_couscous.couscousdrive.view.FragmentFactory;

/**
 * Main controller responsible for toggle buttons, changing fragments.
 */
public class MainController {
    private IFragmentChanger mFragmentReplacer;

    private JoystickController mJoystickController;
    private ACCController mACCController;
    private PlatoonController mPlatoonController;
    private CarCom mCarCom;

    public MainController(CarCom carCom) {
        mCarCom = carCom;
    }

    public void setToggleButtonListener(final ToggleGroup toggleGroup) {
        toggleGroup.setOnCheckedChangeListener(new ToggleGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(ToggleGroup group, @IdRes int[] checkedId) {
                switch (toggleGroup.getCheckedId()) {
                    case (R.id.manual_button):
                        replaceFragment(FragmentFactory.createJoystickFragment(mJoystickController));
                        if (isCarCom()) {
                            mCarCom.sendData(mCarCom.MANUAL_KEY);
                        }
                        break;
                    case (R.id.acc_button):
                        replaceFragment(FragmentFactory.createACCFragment(mACCController));

                        if (isCarCom()) {
                            mCarCom.sendData(mCarCom.ACC_KEY);
                        }
                        break;
                    case (R.id.platoon_button):
                        replaceFragment(FragmentFactory.createPlatoonFragment(mPlatoonController));

                        if (isCarCom()) {
                            mCarCom.sendData(mCarCom.PLATOON_KEY);
                        }
                        break;
                    default:
                        break;
                }
            }
        });
    }

    /**
     * This method tells MainActivity to change fragment
     *
     * @param fragment is the fragment you want to display.
     */
    private void replaceFragment(Fragment fragment) {
        if (mFragmentReplacer != null) {
            mFragmentReplacer.replaceFragment(fragment);
        }
    }

    private boolean isCarCom() {
        return mCarCom != null && mCarCom.isConnected();
    }

    public void setFragmentReplacer(IFragmentChanger fragmentReplacer) {
        mFragmentReplacer = fragmentReplacer;
    }

    public void setJoystickController(JoystickController joystickController) {
        mJoystickController = joystickController;
    }

    public void setACCController(ACCController ACCController) {
        mACCController = ACCController;
    }

    public void setPlatoonController(PlatoonController platoonController) {
        mPlatoonController = platoonController;
    }
}