package potential_couscous.couscousdrive.view;

import com.github.anastr.speedviewlib.TubeSpeedometer;

import io.github.controlwear.virtual.joystick.android.JoystickView;

/**
 * Interface for communication with JoystickController
 */
public interface IJoystick {
    void setJoystickViewListener(JoystickView joystickView);

    void setTubeSpeedometerListener(TubeSpeedometer velocityMeter);
}