package potential_couscous.couscousdrive.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.anastr.speedviewlib.TubeSpeedometer;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import potential_couscous.couscousdrive.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoystickFragment extends Fragment {
    private IJoystick mController;

    public JoystickFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_joy_stick, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setJoystickViewListener(view);
        setTubeSpeedometerListener(view);
        setCalibrationButtons(view);
        setCheckboxViewListener(view);
    }

    private void setCheckboxViewListener(View view) {
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.reverse_box);

        if (isController()) {
            mController.setReverseBox(checkBox);
        }
        checkBox.setChecked(false);
    }

    private void setJoystickViewListener(View view) {
        JoystickView joystickView = (JoystickView) view.findViewById(R.id.joystick_view);

        if (isController()) {
            mController.setJoystickViewListener(joystickView);
        }
    }

    private void setTubeSpeedometerListener(View view) {
        TubeSpeedometer velocityMeter = (TubeSpeedometer) view.findViewById(R.id.velocity_manual_meter);

        if (isController()) {
            mController.setTubeSpeedometerListener(velocityMeter);
        }
    }

    private void setCalibrationButtons(View view) {
        ImageButton leftButton = (ImageButton) view.findViewById(R.id.manual_button_left);
        ImageButton rightButton = (ImageButton) view.findViewById(R.id.manual_button_right);

        TextView calibrationDisplay = (TextView) view.findViewById(R.id.manual_steering_cali);

        if (isController()) {
            mController.setCalibrateButtons(leftButton, rightButton);
            mController.setCalibrateDisplay(calibrationDisplay);
        }
    }

    private boolean isController() {
        return mController != null;
    }

    public void setIController(IJoystick controller) {
        mController = controller;
    }
}