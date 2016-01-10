package model;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public class Point {
    private int coordinateX;
    private int coordinateY;
    private State state;

    public enum State {
        FIELD, DECK, HITTED_DECK, MISS
    }

    public Point(int coordinateX, int coordinateY, State state) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.state = state;
    }

    public Point(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
        this.state=State.FIELD;
    }

    public Point() {
        this.state = State.FIELD;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public boolean equals(Point point) {
        if (this.getCoordinateX() == point.getCoordinateX() & this.getCoordinateY() == point.getCoordinateY()) {
            return true;
        } else {
            return false;
        }
    }
}
