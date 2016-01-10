package model;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public interface IGame {

//    void start();

    boolean isTheEnd();

    void addLocalPlayerFieldObserver(LocalPlayerFieldObserver observer);

    void addEnemyPlayerFieldObserver(EnemyPlayerFieldObserver observer);

    void addStatisticObserver(StatisticListener listener);

    void setEnemyBehaviour(PlayerBehaviour behaviour);

    void setShipPlacing(ShipPlacing shipPlacing);

    void notifyLocalPlayerFieldObservers();

    void notifyEnemyPlayerFieldObservers();

    void notifyStatisticListeners(String status);

    Field getEnemyField();

    Field getPlayerField();

    IPlayer getEnemy();

    IPlayer getPlayer();

    void startGame();

}
