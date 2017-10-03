package potential_couscous.couscousdrive.utils;

public class WirelessInoConveret {

    private WirelessInoConveret() {
    }

    public static String convertData(int steer, int drive) {
        return "V" + String.valueOf(steer) + "H" + String.valueOf(drive);
    }
}
