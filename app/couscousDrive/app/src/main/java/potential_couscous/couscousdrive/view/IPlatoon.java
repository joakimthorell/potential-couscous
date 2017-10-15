package potential_couscous.couscousdrive.view;

import android.widget.ImageView;
import android.widget.TextView;

import com.skyfishjy.library.RippleBackground;

/**
 * Interface for communication with PlatoonController.
 */
public interface IPlatoon {
    void setRippleBackgroundListener(RippleBackground rippleBackground, ImageView imageView);

    void setTogglePlatoonImageViews(ImageView playButton, ImageView stopButton);
}
