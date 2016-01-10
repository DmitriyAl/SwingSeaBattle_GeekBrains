package model;

/**
 * Created by Dmitriy on 04.12.2015.
 */
public class EnemyShot implements Runnable {
    private IGame model;

    public EnemyShot(IGame model) {
        this.model = model;
    }

    @Override
    public void run() {
        do {
            model.getEnemy().makeShot();
            pause(500);
        } while (!model.isTheEnd());
    }

    private void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
