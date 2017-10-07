package potential_couscous.couscousdrive.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import potential_couscous.couscousdrive.R;
import potential_couscous.couscousdrive.controllers.IACC;
import potential_couscous.couscousdrive.controllers.IJoystick;

public class ACCFragment extends Fragment {
    private IACC mController;

    public ACCFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acc, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setACCButtonListeners(view);
    }

    private void setACCButtonListeners(View view) {
        ImageButton left = (ImageButton) view.findViewById(R.id.left_button);
        ImageButton right = (ImageButton) view.findViewById(R.id.right_button);
        ImageButton up = (ImageButton) view.findViewById(R.id.up_button);
        ImageButton down = (ImageButton) view.findViewById(R.id.down_button);

        if (mController != null) {
            mController.setACCButtonListeners(left, right, up, down);
        }
    }

    public void setIController(IACC controller) {
        mController = controller;
    }
}
