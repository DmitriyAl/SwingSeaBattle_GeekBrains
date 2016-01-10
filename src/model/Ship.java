package model;

import java.util.ArrayList;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public class Ship {
    private String name;
    private int length;
    private boolean isVertical;
    private ArrayList<Point> coordinates;
    private Point[] coordinatesForProgram;

    Ship() {
        this.coordinates = new ArrayList<>();
    }

    public Point[] getCoordinatesForProgram() {
        return coordinatesForProgram;
    }

    public void setCoordinatesForProgram(Point[] coordinatesForProgram) {
        this.coordinatesForProgram = coordinatesForProgram;
    }

    public void setIsVertical(boolean vertical) {
        isVertical = vertical;
    }

    public boolean getIsVertical() {
        return isVertical;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCoordinates(ArrayList<Point> coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<Point> getCoordinates() {
        return coordinates;
    }
}
