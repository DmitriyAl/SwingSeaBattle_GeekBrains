package model;

import java.util.ArrayList;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public interface IPlayer {
    void placeShips(Field field, ArrayList<Ship> ships);

    void setShotAbility(boolean myShot);

    void setPlayerBehaviour(PlayerBehaviour behaviour);

    void setPlayerShipPlacingModel(ShipPlacing shipPlacing);

    void makeShot();

    Field getField();

    boolean getShotAbility();
}
