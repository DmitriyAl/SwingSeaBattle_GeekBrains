package model;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public class HumanPlayer implements PlayerBehaviour{

//    public HumanPlayer(ISwingViewController controller, String shipPlacing) {
//        super.controller = controller;
//        switch (shipPlacing) {
//            case "manual":
//                controller.shipPlacingMessage();
//            case "auto":
//                this.shipPlacing = new AutoShipPlacing();
//        }
//
//    }


    @Override
    public boolean makeShot() {
        return true;
    }
}
