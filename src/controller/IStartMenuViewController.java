package controller;

import model.PlayerBehaviour;
import model.ShipPlacing;
import model.IPlayer;

/**
 * Created by Dmitriy on 12/2/2015.
 */
public interface IStartMenuViewController {

    void setOpponent(PlayerBehaviour behaviour);

    void setShipPlacingMode(ShipPlacing shipPlacingMode);

    void setFieldSize(int size);

    void startGame();

}
