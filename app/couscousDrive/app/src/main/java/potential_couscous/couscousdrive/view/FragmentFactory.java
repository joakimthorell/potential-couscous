package potential_couscous.couscousdrive.view;

public class FragmentFactory {

    public static ACCFragment createACCFragment(IACC accController) {
        ACCFragment accFragment = new ACCFragment();
        accFragment.setIController(accController);
        return accFragment;
    }

    public static JoystickFragment createJoystickFragment(IJoystick joystickController) {
        JoystickFragment joystickFragment = new JoystickFragment();
        joystickFragment.setIController(joystickController);
        return joystickFragment;
    }

    public static PlatoonFragment createPlatoonFragment(IPlatoon platoonController) {
        PlatoonFragment platoonFragment = new PlatoonFragment();
        platoonFragment.setIController(platoonController);
        return platoonFragment;
    }
}
