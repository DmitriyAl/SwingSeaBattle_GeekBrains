package model;

import java.util.ArrayList;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public class AutoShipPlacing implements ShipPlacing {
    @Override
    public void placeShips(Field field, ArrayList<Ship> ships) {
        for (Ship ship : ships) {
            System.out.println(ship.getName());
            System.out.println(ship.getIsVertical());
            ArrayList<Point> decks;
            do {
                decks = new ArrayList<>();
                int coordinateInDirection = (int) (Math.random() * (field.getFieldSize() - ship.getLength() + 1));
                int orthogonalCoordinate = (int) (Math.random() * field.getFieldSize());
                for (int i = 0; i < ship.getLength(); i++) {
                    Point point = new Point();
                    point.setState(Point.State.DECK);
                    if (ship.getIsVertical()) {
                        point.setCoordinateX(orthogonalCoordinate);
                        point.setCoordinateY(coordinateInDirection++);
                    } else {
                        point.setCoordinateX(coordinateInDirection++);
                        point.setCoordinateY(orthogonalCoordinate);
                    }
                    decks.add(point);
//                    System.out.println(point.getCoordinateX()+ " " + point.getCoordinateY());
                }
            } while (!isPlaced(field, decks));
            for (int i = 0; i < decks.size(); i++) {
                System.out.println(decks.get(i).getCoordinateX() + " " + decks.get(i).getCoordinateY());
                ship.getCoordinatesForProgram()[i] = decks.get(i);
            }
            ship.setCoordinates(decks);
            field.sendAsDesignerField(decks);
            field.sendAsGameField(decks);
        }
    }

    private boolean isPlaced(Field field, ArrayList<Point> decks) {
        boolean isPlaced = true;
        for (Point deck : decks) {
            if (field.getFieldForDesigner()[deck.getCoordinateY()][deck.getCoordinateX()].getState() != Point.State.FIELD) {
                isPlaced = false;
            }
        }
        return isPlaced;
    }

//    private boolean isPlaced(Field field, ArrayList<Point> decks) {
//        boolean isPlaced = true;
//        for (int i = 0; i < decks.size(); i++) {
//            if (field.getFieldForDesigner()[decks.get(i).getCoordinateY()][decks.get(i).getCoordinateX()].getState() != Point.State.FIELD) {
//                isPlaced = false;
//            }
//        }
//        return isPlaced;
//    }
}
