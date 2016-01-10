package controller;

import model.*;
import view.StartMenuView;

/**
 * Created by Dmitriy on 12/2/2015.
 */
public class StartMenuViewController implements IStartMenuViewController {
    private IGame model;
    private StartMenuView view;
    private static StartMenuViewController instance;

    private StartMenuViewController(IGame model) {
        this.model = model;
        view = StartMenuView.getInstance(this, model);
    }

    public static StartMenuViewController getInstance(IGame model) {
        if (instance == null) {
            instance = new StartMenuViewController(model);
        }
        return instance;
    }

    @Override
    public void setOpponent(PlayerBehaviour behaviour) {
        model.setEnemyBehaviour(behaviour);
    }

    @Override
    public void setShipPlacingMode(ShipPlacing shipPlacingMode) {
        model.setShipPlacing(shipPlacingMode);
    }

    @Override
    public void setFieldSize(int size) {
        Field.setFieldSize(size);
        Field.setFieldSize(size);
    }

    @Override
    public void startGame() {
        model.getEnemyField().initField();
        model.getPlayerField().initField();
        model.getEnemy().placeShips(model.getEnemyField(), model.getEnemyField().getShips());
        model.getPlayer().placeShips(model.getPlayerField(),model.getPlayerField().getShips());
        GameViewController gameViewController = GameViewController.getInstance(model);
        model.startGame();
    }
}
