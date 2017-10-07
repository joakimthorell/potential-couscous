package potential_couscous.couscousdrive.controllers;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ACCController implements IACC{

    public ACCController() {
    }

    private void setLeftButtonListener(ImageButton leftButton) {
        leftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(": LEFT :");

            }
        });
    }

    private void setRightButtonListener(ImageButton rightButton) {
        rightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(": RIGHT :");

            }
        });
    }

    private void setUpButtonListener(ImageButton upButton) {
        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(": UP :");

            }
        });
    }

    private void setDownButtonListener(ImageButton downButton) {
        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(": DOWN :");

            }
        });
    }

    @Override
    public void setACCButtonListeners(ImageButton left, ImageButton right, ImageButton up, ImageButton down) {
        setLeftButtonListener(left);
        setRightButtonListener(right);
        setUpButtonListener(up);
        setDownButtonListener(down);
    }
}
