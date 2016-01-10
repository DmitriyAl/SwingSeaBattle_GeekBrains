package view;

import controller.IGameViewController;
import model.*;
import model.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Dmitriy on 20.11.2015.
 */
public class GameView extends JFrame implements EnemyPlayerFieldObserver, LocalPlayerFieldObserver, StatisticListener {

    private IGameViewController controller;
    private IGame model;
    //    private JFrame window;
    private JPanel background;
    private JPanel playerField;
    private JPanel enemyField;
    private JPanel statistic;
    private MyJPanel[][] playerFieldButtons;
    private MyJPanel[][] enemyFieldButtons;
    private JLabel status;
    private boolean isTheEnd;
    private static GameView instance;
    public static final int SCREEN_WIDTH = 1000;
    public static final int SCREEN_HEIGHT = 400;
    public static final int GRAPHIC_FIELD_SIZE = 300;
    private int pointSize;
    public static int fieldDimension;

    private GameView(IGameViewController controller, IGame model) {
        this.model = model;
        this.controller = controller;
        this.background = new JPanel();
        this.playerField = new JPanel();
        this.enemyField = new JPanel();
        this.statistic = new JPanel();
        fieldDimension = Field.getFieldSize();
        this.model.addLocalPlayerFieldObserver(this);
        this.model.addEnemyPlayerFieldObserver(this);
        this.model.addStatisticObserver(this);
        setupFrame();
        setupPlayerField();
        setupEnemyField();
        setVisible(true);
    }

    public static GameView getInstance(IGameViewController controller, IGame model) {
        if (instance == null) {
            instance = new GameView(controller, model);
        }
        return instance;
    }

    public MyJPanel[][] getPlayerFieldButtons() {
        return playerFieldButtons;
    }

    public void setPlayerFieldButtons(MyJPanel[][] playerFieldButtons) {
        this.playerFieldButtons = playerFieldButtons;
    }

    public MyJPanel[][] getEnemyFieldButtons() {
        return enemyFieldButtons;
    }

    public void setupFrame() {
        this.pointSize = GRAPHIC_FIELD_SIZE / fieldDimension;
        this.playerFieldButtons = new MyJPanel[fieldDimension][fieldDimension];
        this.enemyFieldButtons = new MyJPanel[fieldDimension][fieldDimension];
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);


        this.background.setBackground(new Color(202, 202, 202));
        this.background.setBounds(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        add(background);
        this.background.setLayout(null);
        this.playerField.setLayout(null);
        this.enemyField.setLayout(null);
        this.playerField.setBackground(new Color(202, 202, 202));
        this.playerField.setBounds(30, 30, GRAPHIC_FIELD_SIZE, GRAPHIC_FIELD_SIZE);
        this.enemyField.setBackground(new Color(202, 202, 202));
        this.enemyField.setBounds(380, 30, GRAPHIC_FIELD_SIZE, GRAPHIC_FIELD_SIZE);
        this.background.add(playerField);
        this.background.add(enemyField);
        this.statistic.setBounds(760, 30, 150, 200);
        status = new JLabel();
        statistic.add(status);
        this.background.add(statistic);

        JMenuBar menuBar = new JMenuBar();
        add(BorderLayout.NORTH, menuBar);

        JMenu menuGame = new JMenu("Game");
        menuBar.add(menuGame);

        JMenuItem itemNewGame = new JMenuItem("New game");
        itemNewGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        menuGame.add(itemNewGame);

        JMenuItem itemExit = new JMenuItem("Exit");
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuGame.add(itemExit);

        JMenu menuProperties = new JMenu("Properties");
        menuBar.add(menuProperties);

        JMenu menuHelp = new JMenu("Help");
        menuBar.add(menuHelp);

        JMenuItem itemAbout = new JMenuItem("About");
        menuBar.add(itemAbout);
    }

    public void setupPlayerField() {
        Field field = model.getPlayerField();
        Point[][] fieldWithShips = field.getFieldWithShips();
        for (int i = 0, k = 0; i < pointSize * fieldDimension; i += pointSize, k++) {
            for (int j = 0, l = 0; j < pointSize * fieldDimension; j += pointSize, l++) {
                MyJPanel button = new MyJPanel();
                button.setLayout(null);
                switch (fieldWithShips[k][l].getState()) {
                    case DECK:
                        button.setBackground(new Color(0, 255, 0));
                        button.getPoint().setState(Point.State.DECK);
                        break;

                    case FIELD:
                        button.setBackground(new Color(255, 250, 211));
                        button.getPoint().setState(Point.State.FIELD);
                        break;
                }
                button.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                button.setBounds(j, i, pointSize, pointSize);
                playerField.add(button);
                playerFieldButtons[l][k] = button;
            }
        }
    }

    public void setupEnemyField() {
        for (int i = 0, k = 0; i < pointSize * fieldDimension; i += pointSize, k++) {
            for (int j = 0, l = 0; j < pointSize * fieldDimension; j += pointSize, l++) {
                MyJPanel button = new MyJPanel();
                button.setLayout(null);
                button.setPoint(l, k, Point.State.FIELD);
                button.setBackground(new Color(255, 250, 211));

                button.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                button.setBounds(j, i, pointSize, pointSize);
                enemyField.add(button);
                enemyFieldButtons[l][k] = button;
                button.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (model.getPlayer().getShotAbility()) {
                            controller.makeShot(e);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        e.getComponent().setBackground(new Color(243, 250, 57));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        e.getComponent().setBackground(new Color(255, 250, 211));
                    }
                });
            }
        }
    }


    public void redrawEnemyField() {
        for (int i = 0; i < GameView.fieldDimension; i++) {
            for (int j = 0; j < GameView.fieldDimension; j++) {
                switch (this.model.getEnemyField().getGameField()[j][i].getState()) {
                    case FIELD:
                        enemyFieldButtons[i][j].setBackground(new Color(255, 250, 211));
                        break;
                    case HITTED_DECK:
                        enemyFieldButtons[i][j].setBackground(new Color(255, 0, 0));
                        break;
                    case MISS:
                        enemyFieldButtons[i][j].setBackground(new Color(207, 201, 186));
                        enemyFieldButtons[i][j].add(setMissPoint());
                        break;
                }
            }
        }
    }

    public void redrawLocalPlayerField() {
        for (int i = 0; i < GameView.fieldDimension; i++) {
            for (int j = 0; j < GameView.fieldDimension; j++) {
                switch (this.model.getPlayerField().getFieldWithShips()[j][i].getState()) {
                    case FIELD:
                        playerFieldButtons[i][j].setBackground(new Color(255, 250, 211));
                        break;
                    case HITTED_DECK:
                        playerFieldButtons[i][j].setBackground(new Color(255, 0, 0));
                        break;
                    case MISS:
                        playerFieldButtons[i][j].setBackground(new Color(207, 201, 186));
                        playerFieldButtons[i][j].add(setMissPoint());
                        break;
                }
            }
        }
    }

    public JPanel setMissPoint() {
        JPanel point = new JPanel();
        point.setBackground(Color.BLACK);
        point.setBounds((pointSize - pointSize / 2) / 2, (pointSize - pointSize / 2) / 2, pointSize / 2, pointSize / 2);
        return point;
    }


    @Override
    public void updateEnemyPlayerField() {
        redrawEnemyField();
    }

    @Override
    public void updateLocalPlayerField() {
        redrawLocalPlayerField();
    }

    @Override
    public void updateStatus(String status) {
        this.status.setText(status);
    }
}
