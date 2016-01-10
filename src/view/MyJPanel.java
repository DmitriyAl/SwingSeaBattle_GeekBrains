package view;

import model.Point;

import javax.swing.*;

/**
 * Created by Dmitriy on 22.11.2015.
 */
public class MyJPanel extends JPanel {
    private Point point;

    public MyJPanel() {
        this.point = new Point();
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(int coordinateX, int coordinateY, Point.State state) {
        this.point.setCoordinateX(coordinateX);
        this.point.setCoordinateY(coordinateY);
        this.point.setState(state);
    }


//    public void redraw(Point[][] fieldWithShips, Point coordinate, int pointSize) {
//        for (int i = 0; i < GameView.fieldDimension; i++) {
//            for (int j = 0; j < GameView.fieldDimension; j++) {
//                switch (fieldWithShips[j][i].getState()) {
//                    case FIELD:
//                        this.setBackground(new Color(255, 250, 211));
//                        break;
//                    case HITTED_DECK:
//                        this.setBackground(new Color(255, 0, 0));
//                        break;
//                    case MISS:
//                        this.add(setMissPoint(pointSize));
//                        break;
//                }
//            }
//        }
//    }

//    public JPanel setMissPoint(int pointSize) {
//        JPanel point = new JPanel();
//        point.setBackground(Color.BLACK);
//        point.setBounds((pointSize - pointSize / 2) / 2, (pointSize - pointSize / 2) / 2, pointSize / 2, pointSize / 2);
//        return point;
//    }
}

