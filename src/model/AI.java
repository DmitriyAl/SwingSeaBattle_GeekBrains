package model;

/**
 * Created by Dmitriy on 26.11.2015.
 */
public class AI implements PlayerBehaviour {

//    public AI(ISwingViewController controller) {
//        this.shipPlacing = new AutoShipPlacing();
//        this.controller = controller;
//        this.shot = new Point(0, 0, Point.State.FIELD);
//        this.originOfHit = new Point(0, 0, Point.State.HITTED_DECK);
//    }

    @Override
    public boolean makeShot() {
//        int shipAmount;
//        boolean verticalAttempt;
//        pause(500);
//        Point closeToShot;
//        Random random = new Random();
//        int coordinateX;
//        int coordinateY;
//        int attempt = 0;
//        shipAmount = controller.getGame().getPlayerField().getShips().size();
//
//        if (shot.getState() == Point.State.HITTED_DECK) {
//            do {
//                if (tryVerticalShot) {
//                    coordinateX = shot.getCoordinateX();
//                    if (coordinateX > 9)
//                        coordinateX = 9;
//                    if (coordinateX < 0)
//                        coordinateX = 0;
//                    coordinateY = shot.getCoordinateY() - 1 + random.nextInt(3);
//                    if (coordinateY > 9)
//                        coordinateY = 9;
//                    if (coordinateY < 0)
//                        coordinateY = 0;
//                    closeToShot = new Point(coordinateX, coordinateY, Point.State.FIELD);
//                    verticalAttempt = true;
//                } else {
//                    coordinateX = shot.getCoordinateX() - 1 + random.nextInt(3);
//                    if (coordinateX > 9)
//                        coordinateX = 9;
//                    if (coordinateX < 0)
//                        coordinateX = 0;
//                    coordinateY = shot.getCoordinateY();
//                    if (coordinateY > 9)
//                        coordinateY = 9;
//                    if (coordinateY < 0)
//                        coordinateY = 0;
//                    closeToShot = new Point(coordinateX, coordinateY, Point.State.FIELD);
//                    verticalAttempt = false;
//                }
//                if (attempt++ > 10) {
//                    attempt = 0;
//                    tryVerticalShot = !tryVerticalShot;
//                    shot = originOfHit;
//                    break;
//                }
//            } while (!this.field.isAvailableForShot(closeToShot));
//        } else {
//            do {
//                closeToShot = new Point(random.nextInt(10), random.nextInt(10), Point.State.FIELD);
//                verticalAttempt = random.nextBoolean();
//            } while (!this.field.isAvailableForShot(closeToShot));
//        }
//        System.out.println(closeToShot.getCoordinateX() + " " + closeToShot.getCoordinateY()); //todo
//        boolean isDamaged = controller.getGame().getPlayerField().checkShot(closeToShot);
//        controller.getGame().getPlayerField().showField(controller.getGame().getPlayerField().getFieldWithShips()); //todo
//        controller.getSwingView().redraw(controller.getSwingView().getPlayerFieldButtons(), controller.getGame().getPlayerField().getFieldWithShips());
//        if (isDamaged) {
//            if (++repeatedHit == 1) {
//                originOfHit = closeToShot;
//            }
//            shot = closeToShot;
//            tryVerticalShot = verticalAttempt;
//            shot.setState(Point.State.HITTED_DECK);
//            int currentShipAmount = controller.getGame().getPlayerField().getShips().size();
//            if (shipAmount != currentShipAmount) {
//                shot.setState(Point.State.FIELD);
//                tryVerticalShot = random.nextBoolean();
//            }
//        }
//        controller.getGame().getPlayerField().showField(controller.getGame().getPlayerField().getFieldWithShips());
//        System.out.println(shot.getCoordinateX() + " " + shot.getCoordinateY());
        return true;
    }
}
