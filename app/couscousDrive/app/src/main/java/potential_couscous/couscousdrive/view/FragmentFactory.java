package potential_couscous.couscousdrive.view;

import potential_couscous.couscousdrive.controllers.ACCController;
import potential_couscous.couscousdrive.controllers.JoystickController;

public class FragmentFactory {

    public static ACCFragment createACCFragment(ACCController accController) {
        ACCFragment accFragment = new ACCFragment();
        accFragment.setIController(accController);
        return accFragment;
    }

    public static JoystickFragment createJoystickFragment(JoystickController joystickController) {
        JoystickFragment joystickFragment = new JoystickFragment();
        joystickFragment.setIController(joystickController);
        return joystickFragment;
    }
}
