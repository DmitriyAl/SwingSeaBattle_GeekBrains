package model;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public class ShipFactory {
    private Ship ship;

    public ArrayList<Ship> createShips() {
        ArrayList<Ship> ships = new ArrayList<>();
        int longestShip = determineAmount();
        int currentLength = longestShip;
        int placedShipsOfThisType;
        do {
            placedShipsOfThisType = 0;
            int availableAmountOtThisTypeShip;
            do {
                availableAmountOtThisTypeShip = longestShip - currentLength - placedShipsOfThisType++ + 1;
                ship = new Ship();
                ship.setLength(currentLength);
                ship.setCoordinatesForProgram(new Point[currentLength]);
                ship.setIsVertical(isVertical());
                switch (currentLength) {
                    case 4:
                        ship.setName("four deck");
                        break;
                    case 3:
                        ship.setName("three deck");
                        break;
                    case 2:
                        ship.setName("two deck");
                        break;
                    case 1:
                        ship.setName("one deck");
                        break;
                    default:
                        ship.setName("this");
                        break;
                }
//                System.out.println(ship.getName() + " "+ ship.getLength());
                ships.add(ship);
                availableAmountOtThisTypeShip--;
            } while (availableAmountOtThisTypeShip != 0);
            currentLength--;
        } while (currentLength != 0);
        return ships;
    }

    public int determineAmount() {
        int decks = 0;
        int longest;
        int field = Field.getFieldSize() * Field.getFieldSize();
        for (longest = 1; longest < Integer.MAX_VALUE; longest++) {
            for (int j = 1; j <= longest; j++) {
                decks += j;
                if (field / decks <= 5) {
                    return longest;
                }
            }
        }
//        return longest;
        return 0;
    }

    private boolean isVertical() {
        Random r = new Random();
        return r.nextBoolean();
    }
}
