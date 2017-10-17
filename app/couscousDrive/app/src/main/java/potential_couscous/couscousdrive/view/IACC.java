package potential_couscous.couscousdrive.view;

import android.widget.ImageButton;
import android.widget.TextView;

import com.github.anastr.speedviewlib.TubeSpeedometer;

/**
 * Interface for communication with ACCController
 */
public interface IACC {
    void setACCImageButtonListeners(ImageButton left, ImageButton right, ImageButton up, ImageButton down);

    void setACCTubeSpeedometerListeners(TubeSpeedometer velocityMeter, TubeSpeedometer angleMeter);

    void setSteerTextView(TextView textView);

    void setDriveTextView(TextView textView);
}