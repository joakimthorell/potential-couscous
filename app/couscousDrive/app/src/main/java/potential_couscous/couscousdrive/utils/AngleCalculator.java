/**
 * This class calculates the angle from joystick to an input for moped
 * Constants max & min value are the values for steer on moped.
 */

package potential_couscous.couscousdrive.utils;

public class AngleCalculator {
    public static int MAX_VALUE = 100;
    public static int MIN_VALUE = -100;

    private AngleCalculator() {    }

    /**
     * Calculates the angle that MOPED can use
     * @param angle value of joystick from origo.
     * @return <b>int</b> value between MAX_VALUE and MIN_VALUE
     */
    public static int calcAngle(int angle) {
        if (angle < 0 || angle > 360) {
            return 0;
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
        double num = multiplyQuote(90 - angle);
        return roundDouble(num);
    }

    private static int secondQuadrant(int angle) {
        double num = multiplyNegativeQuote(angle - 90);
        return roundDouble(num);
    }

    private static int thirdQuadrant(int angle) {
        double num = multiplyNegativeQuote(270 - angle);
        return roundDouble(num);
    }

    private static int fourthQuadrant(int angle) {
        double num = multiplyQuote(angle - 270);
        return roundDouble(num);
    }

    private static double multiplyQuote(double value) {
        return MAX_VALUE * value / 90;
    }

    private static double multiplyNegativeQuote(double value) {
        return MIN_VALUE * value / 90;
    }

    public static int roundDouble(double d) {
        return (int) Math.round(d);
    }

    /**
     * Calculates speed depending on Joystick angle.
     * @param angle angle from joystick
     * @param speed from joystick
     * @return -speed or positive speed
     */
    public static int calcSpeed(int angle, int speed) {
        if (angle <= 180) {
            return speed;
        }
        return speed * -1;
    }
}