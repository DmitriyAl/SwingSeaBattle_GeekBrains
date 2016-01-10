package model;

import java.util.ArrayList;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public class Player implements IPlayer {
    private String name;
    private Field field;
    private ShipPlacing shipPlacing;
    private PlayerBehaviour behaviour;
    private volatile boolean myShot;
//    protected Point shot;
//    protected boolean tryVerticalShot;
//    protected Point originOfHit;
//    protected int repeatedHit;

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public boolean getShotAbility() {
        return myShot;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public void placeShips(Field field, ArrayList<Ship> ships) {
        shipPlacing.placeShips(field, ships);
    }

    public void setShotAbility(boolean myShot) {
        this.myShot = myShot;
    }

    @Override
    public void setPlayerBehaviour(PlayerBehaviour behaviour) {
        this.behaviour = behaviour;
    }

    @Override
    public void setPlayerShipPlacingModel(ShipPlacing shipPlacing) {
        this.shipPlacing = shipPlacing;
    }


    public void makeShot() {
        if (myShot) {
            myShot = behaviour.makeShot();
        }
    }

    protected static void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
