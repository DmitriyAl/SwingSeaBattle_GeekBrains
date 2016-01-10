package model;

import java.util.ArrayList;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public class Field {
    private IGame game;
    private ShipFactory factory;
    private Point[][] fieldWithShips;
    private Point[][] gameField;
    private Point[][] fieldForDesigner;
    private Point[][] fieldToShowDestroyedShip;
    private ArrayList<Ship> ships;
    private static int size;

    public Field(IGame game) {
        this.game = game;
        this.factory = new ShipFactory();
    }

    public static int getFieldSize() {
        return size;
    }

    public static void setFieldSize(int size) {
        Field.size = size;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    public Point[][] getFieldForDesigner() {
        return fieldForDesigner;
    }

    public Point[][] getFieldWithShips() {
        return fieldWithShips;
    }

    public Point[][] getGameField() {
        return gameField;
    }

    public Point[][] getFieldToShowDestroyedShip() {
        return fieldToShowDestroyedShip;
    }

    public void setFieldWithShips(Point[][] fieldWithShips) {
        this.fieldWithShips = fieldWithShips;
    }


    public void setFieldForDesigner(Point[][] fieldForDesigner) {
        this.fieldForDesigner = fieldForDesigner;
    }

    public void initField() {
        this.fieldForDesigner = new Point[size][size];
        this.fieldWithShips = new Point[size][size];
        this.gameField = new Point[size][size];
        this.fieldToShowDestroyedShip = new Point[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                fieldWithShips[i][j] = new Point();
                gameField[i][j] = new Point();
                fieldForDesigner[i][j] = new Point();
                fieldToShowDestroyedShip[i][j] = new Point();
            }
        }
        this.ships = factory.createShips();
    }

    public void showFieldWithShips() {
        this.showField(this.fieldWithShips);
    }
    public void showGameField() {
        this.showField(this.gameField);
    }
    public void showFieldForDesigner() {
        this.showField(this.fieldForDesigner);
    }
    public void showFieldToShowDestroyedShips() {
        this.showField(this.fieldToShowDestroyedShip);
    }


    private void showField(Point[][] field) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Point.State state = field[i][j].getState();
                switch (state) {
                    case FIELD:
                        System.out.print(". ");
                        break;
                    case DECK:
                        System.out.print("# ");
                        break;
                    case HITTED_DECK:
                        System.out.print("@ ");
                        break;
                    case MISS:
                        System.out.print("& ");
                        break;
                }
            }
            System.out.println();
        }
    }

    public void sendAsDesignerField(ArrayList<Point> decks) {
        Point origin = decks.get(0);
        Point end = decks.get(decks.size() - 1);
        int originX = origin.getCoordinateX() - 1;
        if (originX < 0) {
            originX = 0;
        }
        int originY = origin.getCoordinateY() - 1;
        if (originY < 0) {
            originY = 0;
        }
        int endX = end.getCoordinateX() + 1;
        if (endX > size - 1) {
            endX = size - 1;
        }
        int endY = end.getCoordinateY() + 1;
        if (endY > size - 1) {
            endY = size - 1;
        }
        for (int i = originX; i <= endX; i++) {
            for (int j = originY; j <= endY; j++) {
                this.fieldForDesigner[j][i].setState(Point.State.MISS);
            }
        }
    }

    public void sendAsDesignerField(Point[][] field, Point point, Point.State state) {
        int originX = point.getCoordinateX() - 1;
        if (originX < 0) {
            originX = 0;
        }
        int originY = point.getCoordinateY() - 1;
        if (originY < 0) {
            originY = 0;
        }
        int endX = point.getCoordinateX() + 1;
        if (endX > size - 1) {
            endX = size - 1;
        }
        int endY = point.getCoordinateY() + 1;
        if (endY > size - 1) {
            endY = size - 1;
        }
        for (int i = originX; i <= endX; i++) {
            for (int j = originY; j <= endY; j++) {
                field[j][i].setState(state);
            }
        }
    }

    public void sendAsGameField(ArrayList<Point> decks) {
        Point origin = decks.get(0);
        Point end = decks.get(decks.size() - 1);
        int originX = origin.getCoordinateX();
        int originY = origin.getCoordinateY();
        int endX = end.getCoordinateX();
        int endY = end.getCoordinateY();
        for (int i = originX; i <= endX; i++) {
            for (int j = originY; j <= endY; j++) {
                this.fieldWithShips[j][i].setState(Point.State.DECK);
            }
        }
    }

    public void sendAsGameField(Point[][] field, Point point, Point.State state) {
        field[point.getCoordinateY()][point.getCoordinateX()].setState(state);
    }

    public boolean checkShot(Point shot) {
        boolean isDamaged = false;
        if (this.fieldWithShips[shot.getCoordinateY()][shot.getCoordinateX()].getState() == Point.State.DECK) {
            for (Ship ship : ships) {
                for (Point point : ship.getCoordinates()) {
                    if (shot.equals(point)) {
                        isDamaged = true;
                        sendAsGameField(this.gameField, shot, Point.State.HITTED_DECK);
                        sendAsGameField(this.fieldWithShips, shot, Point.State.HITTED_DECK);
                        ship.getCoordinates().remove(point);
                        game.notifyStatisticListeners("Hit");
                        break;
                    }
                }
                if (ship.getCoordinates().isEmpty()) {
                    for (Point point : ship.getCoordinatesForProgram()) {
                        sendAsDesignerField(this.fieldToShowDestroyedShip, point, Point.State.MISS);
                    }
                    for (int i = 0; i < size; i++) {
                        for (int j = 0; j < size; j++) {
                            if (this.fieldToShowDestroyedShip[j][i].getState() == Point.State.MISS & this.fieldWithShips[j][i].getState() != Point.State.DECK & this.fieldWithShips[j][i].getState() != Point.State.HITTED_DECK) {
                                this.gameField[j][i].setState(this.fieldToShowDestroyedShip[j][i].getState());
                                this.fieldWithShips[j][i].setState(this.fieldToShowDestroyedShip[j][i].getState());
                            }
                        }
                    }
                    ships.remove(ship);
                    game.notifyStatisticListeners("Destroyed");
//                    }
                    break;
                }
            }
        } else {
            sendAsGameField(this.gameField, shot, Point.State.MISS);
            sendAsGameField(this.fieldWithShips, shot, Point.State.MISS);
            game.notifyStatisticListeners("Miss");
        }
        return isDamaged;
    }


    public boolean isAvailableForShot(Point shot) {
        Point[][] playerField = this.fieldWithShips;
        if (playerField[shot.getCoordinateY()][shot.getCoordinateX()].getState() == Point.State.MISS || playerField[shot.getCoordinateY()][shot.getCoordinateX()].getState() == Point.State.HITTED_DECK) {
            return false;
        }
        return true;
    }
}
