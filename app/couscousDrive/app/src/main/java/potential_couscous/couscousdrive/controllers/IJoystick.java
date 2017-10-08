package potential_couscous.couscousdrive.controllers;

import com.github.anastr.speedviewlib.TubeSpeedometer;

import io.github.controlwear.virtual.joystick.android.JoystickView;

public interface IJoystick {
    void setJoystickViewListener(JoystickView joystickView);

    void setTubeSpeedometerListener(TubeSpeedometer velocityMeter);
}