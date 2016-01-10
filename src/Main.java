import controller.StartMenuViewController;
import model.GameWithComputer;

/**
 * Created by Dmitriy on 12/2/2015.
 */
public class Main {
    public static void main(String[] args) {
        GameWithComputer game = GameWithComputer.getInstance();
        StartMenuViewController start = StartMenuViewController.getInstance(game);
        System.out.println("smth");

    }
}
