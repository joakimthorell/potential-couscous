package potential_couscous.couscousdrive.controllers;

import android.widget.ImageButton;
import android.widget.TextView;

import com.github.anastr.speedviewlib.TubeSpeedometer;

public interface IACC {
    void setACCImageButtonListeners(ImageButton left, ImageButton right, ImageButton up, ImageButton down);

    void setACCTextView(TextView textView);

    void setACCTextViewListener();

    void setACCTubeSpeedmeterListeners(TubeSpeedometer velocityMeter, TubeSpeedometer angleMeter);
}