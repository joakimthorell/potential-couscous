package potential_couscous.couscousdrive.view;

import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.anastr.speedviewlib.TubeSpeedometer;

import io.github.controlwear.virtual.joystick.android.JoystickView;

/**
 * Interface for communication with JoystickController
 */
public interface IJoystick {
    void setJoystickViewListener(JoystickView joystickView);

    void setTubeSpeedometerListener(TubeSpeedometer velocityMeter);

    void setCalibrateButtons(ImageButton leftButton, ImageButton rightButton);

    void setCalibrateDisplay(TextView calibrateDisplay);

    void setReverseBox(final CheckBox checkBox);
}