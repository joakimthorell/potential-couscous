package potential_couscous.couscousdrive.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.controllers.IJoystick;

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

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setJoystickViewListener(view);
    }

    private void setJoystickViewListener(View view) {
        JoystickView joystickView = (JoystickView) view.findViewById(R.id.joystick_view);

        if (mController != null) {
            mController.setJoystickViewListener(joystickView);
        }
    }

    public void setIController(IJoystick controller) {
        mController = controller;
    }
}