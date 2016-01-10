package model;

import java.util.ArrayList;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public class GameWithComputer implements IGame {
    private ArrayList<LocalPlayerFieldObserver> localPlayerFieldObservers;
    private ArrayList<EnemyPlayerFieldObserver> enemyPlayerFieldObservers;
    private ArrayList<StatisticListener> statisticListeners;

    private Player localPlayer;
    private Player enemyPlayer;
    private Field localPlayerField;
    private Field enemyPlayerField;
    private static GameWithComputer instance;

    private GameWithComputer() {
        localPlayerFieldObservers = new ArrayList<>();
        enemyPlayerFieldObservers = new ArrayList<>();
        statisticListeners = new ArrayList<>();
        init();
    }

    public static GameWithComputer getInstance() {
        if (instance == null) {
            instance = new GameWithComputer();
        }
        return instance;
    }

    public Field getLocalPlayerField() {
        return localPlayerField;
    }

    public Field getEnemyPlayerField() {
        return enemyPlayerField;
    }

    public void setShipPlacing(ShipPlacing shipPlacing) {
        localPlayer.setPlayerShipPlacingModel(shipPlacing);
    }

    @Override
    public void setEnemyBehaviour(PlayerBehaviour behaviour) {
        enemyPlayer.setPlayerBehaviour(behaviour);
    }

    private void init() {
        localPlayer = new Player();
        localPlayer.setPlayerBehaviour(new HumanPlayer());
        enemyPlayer = new Player();
        enemyPlayer.setPlayerShipPlacingModel(new AutoShipPlacing());
        localPlayerField = new Field(this);
        enemyPlayerField = new Field(this);
        localPlayer.setField(localPlayerField);
        enemyPlayer.setField(enemyPlayerField);
    }

//    @Override
//    public void start() {
//        localPlayer.placeShips(localPlayerField, localPlayerField.getShips());
//        enemyPlayer.placeShips(enemyPlayerField, enemyPlayerField.getShips());
////        enemyPlayerField.showField(enemyPlayerField.getFieldWithShips());
//        do {
//            enemyPlayer.makeShot();
//        } while (!isTheEnd());
//    }

    @Override
    public boolean isTheEnd() {
        if (enemyPlayerField.getShips().isEmpty() || localPlayerField.getShips().isEmpty()) {
            notifyStatisticListeners("The End");
//            System.out.println("The End");
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addLocalPlayerFieldObserver(LocalPlayerFieldObserver observer) {
        localPlayerFieldObservers.add(observer);
    }

    @Override
    public void addEnemyPlayerFieldObserver(EnemyPlayerFieldObserver observer) {
        enemyPlayerFieldObservers.add(observer);
    }


    @Override
    public void notifyLocalPlayerFieldObservers() {
        for (LocalPlayerFieldObserver listener : localPlayerFieldObservers) {
            listener.updateLocalPlayerField();
        }
    }

    @Override
    public void notifyEnemyPlayerFieldObservers() {
        for (EnemyPlayerFieldObserver listener : enemyPlayerFieldObservers) {
            listener.updateEnemyPlayerField();
        }
    }

    @Override
    public void notifyStatisticListeners(String status) {
        for (StatisticListener statisticListener : statisticListeners) {
            statisticListener.updateStatus(status);
        }
    }

    @Override
    public Field getEnemyField() {
        return enemyPlayerField;
    }

    @Override
    public Field getPlayerField() {
        return localPlayerField;
    }

    @Override
    public IPlayer getEnemy() {
        return enemyPlayer;
    }

    @Override
    public IPlayer getPlayer() {
        return localPlayer;
    }

    @Override
    public void startGame() {
        localPlayer.setShotAbility(true);
        Runnable makeEnemyShot = new EnemyShot(this);
        Thread second = new Thread(makeEnemyShot);
        second.start();
    }

    @Override
    public void addStatisticObserver(StatisticListener listener) {
        statisticListeners.add(listener);
    }

}

