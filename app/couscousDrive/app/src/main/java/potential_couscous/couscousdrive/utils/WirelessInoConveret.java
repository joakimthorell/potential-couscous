package potential_couscous.couscousdrive.utils;

/**
 * This class reformates data in order for WirelessIno server to receive it.
 */
public class WirelessInoConveret {

    private WirelessInoConveret() {
    }

    /**
     * This method builds string with speed and steer data, always 10 characters long (VxxxxHxxxx)
     * @param steer
     * @param drive
     * @return
     */
    public static String convertData(int steer, int drive) {
        return "V" + buildData(drive) + "H" + buildData(steer);
    }

    private static String buildData(int value) {
        String data = "";
        if (value >= 0) {
            data += "0"; //Positive values
        } else {
            value *= -1;
            data += "-"; //Negative values
        }
        if (value < 100) {
            data += "0"; //In order to make string string required length
        }
        if (value < 10) {
            data += "0"; //In order to make string string required length
        }
        data += String.valueOf(value);
        return data;
    }
}
