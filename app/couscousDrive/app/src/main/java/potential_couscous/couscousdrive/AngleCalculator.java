/**
 * This class helps calculating the angle from joystick to an input for moped
 * Constants max & min value are the values for steer on moped.
 */

package potential_couscous.couscousdrive;

public class AngleCalculator {
    // max and min value of car drive and steering inputs.
    public static int MAX_VALUE = 100;
    public static int MIN_VALUE = -100;

    private AngleCalculator() {
        // private constructor. Static class.
        // Should not be able to create object of this.
    }

    /**
     * Calculates the angle that MOPED can use
     *
     * @param angle value of joystick from origo.
     * @return <b>int</b> value between MAX_VALUE and MIN_VALUE
     */
    public static int calcAngle(int angle) {
        if (angle < 0 || angle > 360) {
            return 0; // This method could instead calc pi from value.
        }


        if (angle >= 0 && angle <= 90) {
            return firstQuadrant(angle);
        } else if (angle > 90 && angle <= 180) {
            return secondQuadrant(angle);
        } else if (angle > 180 && angle <= 270) {
            return thirdQuadrant(angle);
        } else {
            return fourthQuadrant(angle);
        }

    }

    private static int firstQuadrant(int angle) {
        double num = multiplayQuote(90 - angle);
        return roundDoubleToInt(num);
    }

    private static int secondQuadrant(int angle) {
        double num = multiplayNegativeQuote(angle - 90);
        return roundDoubleToInt(num);
    }

    private static int thirdQuadrant(int angle) {
        double num = multiplayNegativeQuote(270 - angle);
        return roundDoubleToInt(num);
    }

    private static int fourthQuadrant(int angle) {
        double num = multiplayQuote(angle - 270);
        return roundDoubleToInt(num);
    }

    private static double multiplayQuote(double value) {
        return MAX_VALUE * value / 90;
    }

    private static double multiplayNegativeQuote(double value) {
        return MIN_VALUE * value / 90;
    }

    public static int roundDoubleToInt(double d) {
        return (int) Math.round(d);
    }

}
