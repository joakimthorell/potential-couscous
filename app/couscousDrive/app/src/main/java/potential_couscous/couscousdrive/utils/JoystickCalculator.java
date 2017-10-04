/**
 * This class calculates the angle from joystick to an input for moped
 * Constants max & min value are the values for steer on moped.
 */
package potential_couscous.couscousdrive.utils;

public class JoystickCalculator {
    public static int MAX_VALUE = 100;
    public static int MIN_VALUE = -100;

    private JoystickCalculator() {
    }

    /**
     * Calculates the angle that MOPED can use
     *
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
        if (angle == 0) {
            angle = 90;
        }
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
     *
     * @param angle angle from joystick
     * @param velocity from joystick
     * @return -speed or positive speed
     */
    public static int calcSpeed(int angle, int velocity) {
        //velocity = translateSpeed(velocity);
        velocity /= 6; // divide velocity to minimize max velocity
        if (angle <= 180) {
            return velocity;
        }
        return velocity * -1;
    }

    /**
     * Attempt to minimize velocity and send less data to WirelessIno server
     * @param velocity
     * @return
     */
    private static int translateSpeed(int velocity) {
        if (velocity > 0 && velocity <= 25) {
            return 9;
        }
        else if (velocity > 25 && velocity <= 50) {
            return 11;
        } else if (velocity > 50 && velocity <= 75) {
            return 15;
        } else if (velocity > 75 && velocity < 100) {
            return 19;
        } else if (velocity == 100) {
            return 23;
        }
        return 0;
    }
}