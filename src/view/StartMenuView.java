package view;

import controller.IStartMenuViewController;
import model.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Created by Dmitriy on 11/26/2015.
 */
public class StartMenuView extends JFrame {
    private IStartMenuViewController controller;
    private IGame model;
    public static final int SCREEN_WIDTH = 500;
    public static final int SCREEN_HEIGHT = 300;
    private volatile boolean readyToStart;
    private static StartMenuView instance;

    public static StartMenuView getInstance(IStartMenuViewController controller, IGame model) {
        if (instance == null) {
            instance = new StartMenuView(controller, model);
        }
        return instance;
    }

    private StartMenuView(IStartMenuViewController controller, IGame model) {
        this.controller = controller;
        this.model = model;
        startSettings();
    }
//
//    public StartMenuView() throws HeadlessException {
//    }

    private void startSettings() {
        JPanel panel = new JPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2);
        add(panel);

        JPanel gridPanel = new JPanel(new GridLayout(3, 3));
        Box fieldSizePanel = new Box(BoxLayout.X_AXIS);
        panel.setAlignmentX(LEFT_ALIGNMENT);
        panel.add(gridPanel);
        panel.add(fieldSizePanel);


        JLabel nothing = new JLabel();
        JLabel yes = new JLabel("Yes");
        JLabel no = new JLabel("No");

        gridPanel.add(nothing);
        gridPanel.add(yes);
        gridPanel.add(no);

        JLabel choseOpponent = new JLabel("Play with CPU?");
        JCheckBox opYes = new JCheckBox();
        opYes.setSelected(true);
        JCheckBox opNo = new JCheckBox();
        opNo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                opNo.setSelected(false);
                opYes.setSelected(true);
            }
        });
        opYes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                opNo.setSelected(false);
                opYes.setSelected(true);
            }
        });

        gridPanel.add(choseOpponent);
        gridPanel.add(opYes);
        gridPanel.add(opNo);

        JLabel howToPlaceShips = new JLabel("Place ships manually?");
        JCheckBox shYes = new JCheckBox();
        JCheckBox shNo = new JCheckBox();
        shNo.setSelected(true);

        shYes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                shYes.setSelected(false);
                shNo.setSelected(true);
            }
        });
        shNo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                shYes.setSelected(false);
                shNo.setSelected(true);
            }
        });

        gridPanel.add(howToPlaceShips);
        gridPanel.add(shYes);
        gridPanel.add(shNo);

        JLabel fieldSize = new JLabel("Set the field size");
        JSlider slider = new JSlider();
        slider.setMinorTickSpacing(1);
        slider.setMinimum(2);
        slider.setMaximum(40);
        slider.setOrientation(SwingConstants.HORIZONTAL);
        slider.setPreferredSize(new Dimension(200, 50));
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setSnapToTicks(true);
        slider.setValue(10);
        JLabel currentSize = new JLabel("10");

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                currentSize.setText(String.valueOf(slider.getValue()));
            }
        });

        fieldSizePanel.add(fieldSize);
        fieldSizePanel.add(slider);
        fieldSizePanel.add(currentSize);
        fieldSizePanel.setAlignmentX(LEFT_ALIGNMENT);


        JButton startBtn = new JButton("Start game");
        startBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String opponent;
                String shipPlacing;
                if (opYes.isSelected()) {
                    controller.setOpponent(new EasyComputerPlayer(model));
                } else {
                    controller.setOpponent(new HumanPlayer());
                }
                if (shNo.isSelected()) {
                    controller.setShipPlacingMode(new AutoShipPlacing());
                } else {
                    controller.setShipPlacingMode(new ManualShipPlacing());
                }

                controller.setFieldSize(slider.getValue());
                controller.startGame();
//                readyToStart = true;
            }
        });
        panel.add(startBtn);
        setVisible(true);
    }
}
