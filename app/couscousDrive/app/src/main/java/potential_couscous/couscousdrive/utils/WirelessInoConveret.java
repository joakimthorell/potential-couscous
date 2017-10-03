package potential_couscous.couscousdrive.utils;

public class WirelessInoConveret {

    private WirelessInoConveret() {
    }

    public static String convertData(int steer, int drive) {
        System.out.println("V" + buildData(steer) + "H" + buildData(drive));
        return "V" + buildData(steer) + "H" + buildData(drive);
    }

    private static String buildData(int value) {
        String data = "";
        if (value >= 0) {
            data += "0";
        } else {
            value *= -1;
            data += "-";
        }
        if (value < 100) {
            data += "0";
        }
        if (value < 10) {
            data += "0";
        }
        data += String.valueOf(value);
        return data;
    }
}
