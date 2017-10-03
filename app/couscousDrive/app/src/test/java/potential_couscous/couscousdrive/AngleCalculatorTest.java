package potential_couscous.couscousdrive;

import org.junit.Before;
import org.junit.Test;

import potential_couscous.couscousdrive.utils.AngleCalculator;

import static org.junit.Assert.*;

public class AngleCalculatorTest {

    private int DELTA = 1;

    int iAngle;
    double dAngle;

    int angle0To90;
    int angle90To180;
    int angle180To270;
    int angle270To360;

    @Before
    public void setup() {
        iAngle = 80;
        dAngle = 56.79;

        angle0To90 = 32;
        angle90To180 = 117;
        angle180To270 = 220;
        angle270To360 = 286;
    }


    @Test
    public void calcAngle() {

        assertEquals(64, AngleCalculator.calcAngle(angle0To90), DELTA);
        assertEquals(-30, AngleCalculator.calcAngle(angle90To180), DELTA);
        assertEquals(-55, AngleCalculator.calcAngle(angle180To270), DELTA);
        assertEquals(18, AngleCalculator.calcAngle(angle270To360), DELTA);


        // Trying max/min values in first and second quadrant
        assertEquals(100, AngleCalculator.calcAngle(0), DELTA);
        assertEquals(0, AngleCalculator.calcAngle(90), DELTA);
        assertEquals(0, AngleCalculator.calcAngle(91), DELTA);
        assertEquals(0, AngleCalculator.calcAngle(89), DELTA);
        assertEquals(-100, AngleCalculator.calcAngle(180), DELTA);
        assertEquals(-100, AngleCalculator.calcAngle(179), DELTA);

        // Trying max/min values in third and fourth quadrant
        assertEquals(-100, AngleCalculator.calcAngle(181), DELTA);
        assertEquals(0, AngleCalculator.calcAngle(270), DELTA);
        assertEquals(100, AngleCalculator.calcAngle(360), DELTA);

        assertEquals(0, AngleCalculator.calcAngle(-1));
        assertEquals(0, AngleCalculator.calcAngle(2000));

    }

    @Test
    public void roundDoubleToInt() {
        assertEquals(57, dAngle, DELTA);
    }

}