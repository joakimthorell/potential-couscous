package potential_couscous.couscousdrive.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

import potential_couscous.couscousdrive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlatoonFragment extends Fragment {
    private IPlatoon mController;

    public PlatoonFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_platoon, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setTogglePlatoonImageView(view);
        setRippleBackgroundListener(view);
        setPlatoonCalibrationButtons(view);
    }

    private void setRippleBackgroundListener(View view) {
        RippleBackground rippleBackground = (RippleBackground) view.findViewById(R.id.content);
        ImageView imageView = (ImageView) view.findViewById(R.id.centerImage);

        if (isController()) {
            mController.setRippleBackgroundListener(rippleBackground, imageView);
        }
    }

    private void setTogglePlatoonImageView(View view) {
        ImageView playButton = (ImageView) view.findViewById(R.id.play_platoon);
        ImageView stopButton = (ImageView) view.findViewById(R.id.stop_platoon);

        if (isController()) {
            mController.setTogglePlatoonImageViews(playButton, stopButton);
        }
    }

    public void setPlatoonCalibrationButtons(View view) {
        ImageButton left = (ImageButton) view.findViewById(R.id.platoon_button_left);
        ImageButton right = (ImageButton) view.findViewById(R.id.platoon_button_right);

        if (isController()) {
            mController.setPlatoonButtonListeners(left, right);
        }
    }

    private boolean isController() {
        return mController != null;
    }

    public void setIController(IPlatoon controller) {
        mController = controller;
    }

}
