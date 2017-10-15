package potential_couscous.couscousdrive.controllers;

import potential_couscous.couscousdrive.utils.CarCom;

public class AbstractController {

    protected boolean isCarCom(CarCom carCom) {
        return carCom != null && carCom.isConnected();
    }
}
