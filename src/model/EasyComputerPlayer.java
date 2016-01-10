package model;

import java.util.Random;

/**
 * Created by Dmitriy on 21.11.2015.
 */
public class EasyComputerPlayer implements PlayerBehaviour {
    private IGame game;
    private Point shot;


    public EasyComputerPlayer(IGame game) {
        this.game = game;
    }

    @Override
    public boolean makeShot() {
        Random random = new Random();
        do {
            shot = new Point(random.nextInt(Field.getFieldSize()), random.nextInt(Field.getFieldSize()), Point.State.FIELD);
        } while (!this.game.getPlayerField().isAvailableForShot(shot));
        boolean isHit = this.game.getPlayerField().checkShot(shot);
        game.notifyLocalPlayerFieldObservers();
        game.getPlayer().setShotAbility(!isHit);
        return isHit;
    }

    private void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
