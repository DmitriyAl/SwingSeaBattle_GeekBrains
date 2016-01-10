package controller;

import model.Field;
import model.IGame;
import model.Point;
import view.GameView;
import view.MyJPanel;

import java.awt.event.MouseEvent;

/**
 * Created by Dmitriy on 12/2/2015.
 */
public class GameViewController implements IGameViewController {
    private IGame model;
    private GameView view;
    private static GameViewController instance;

    private GameViewController(IGame model) {
        this.model = model;
        view = GameView.getInstance(this, model);
    }

    public static GameViewController getInstance(IGame model) {
        if (instance == null) {
            instance = new GameViewController(model);
        }
        return instance;
    }

    @Override
    public boolean makeShot(MouseEvent e) {
        MyJPanel source = (MyJPanel) e.getSource();
        Point shot = source.getPoint();
        source.removeMouseListener(e.getComponent().getMouseListeners()[0]);
//        model.getEnemyField().showFieldWithShips();
        boolean isHitted = model.getEnemyField().checkShot(shot);
        model.notifyEnemyPlayerFieldObservers();
//        model.getEnemyField().showGameField();
        disableListeners(model.getEnemyField().getGameField());
        model.getEnemy().setShotAbility(!isHitted);
        model.getPlayer().setShotAbility(isHitted);
        return false;
    }

    public void disableListeners(Point[][] gameField) {
        for (int i = 0; i < Field.getFieldSize(); i++) {
            for (int j = 0; j < Field.getFieldSize(); j++) {
                if (gameField[j][i].getState() == Point.State.MISS) {
                    try {
                        this.view.getEnemyFieldButtons()[i][j].removeMouseListener(this.view.getEnemyFieldButtons()[i][j].getMouseListeners()[0]);
                    } catch (Exception e) {
                    }
                }
            }

        }
    }


}
