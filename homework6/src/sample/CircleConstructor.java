package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleConstructor {

    public static Circle[] getCircles(int circleNumber, double minRadius, double maxRadius) {
        Circle[] circles = new Circle[circleNumber + 3];
        for (int i = 0; i < circles.length; i++) {
            circles[i] = new Circle();
        }
        circles[0].setRadius((int)(Math.random() * maxRadius + minRadius));
        circles[0].setCenterX(300);
        circles[0].setCenterY(circles[0].getRadius() + 1);
        circles[0].setFill(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 0));
        circles[0].setStroke(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 1));
        for (int i = 1; i < circleNumber; i++) {
            circles[i].setRadius((int)(Math.random() * maxRadius + minRadius));
            circles[i].setCenterX(300);
            circles[i].setCenterY(circles[i-1].getCenterY() + circles[i-1].getRadius() + circles[i].getRadius());
            circles[i].setFill(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 0));
            circles[i].setStroke(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 1));
        }
        circles[circleNumber].setRadius((int)(Math.random() * (circles[0].getRadius() / 4) + 1));
        circles[circleNumber].setCenterX(circles[0].getCenterX() - (int)(circles[0].getRadius() * 0.4));
        circles[circleNumber].setCenterY(circles[0].getCenterY() - (int)(circles[0].getRadius() * 0.4));
        circles[circleNumber].setFill(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 0));
        circles[circleNumber].setStroke(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 1));

        circles[circleNumber + 1].setRadius((int)(Math.random() * (circles[0].getRadius() / 4) + 1));
        circles[circleNumber + 1].setCenterX(circles[0].getCenterX() + (int)(circles[0].getRadius() * 0.4));
        circles[circleNumber + 1].setCenterY(circles[0].getCenterY() - (int)(circles[0].getRadius() * 0.4));
        circles[circleNumber + 1].setFill(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 0));
        circles[circleNumber + 1].setStroke(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 1));

        circles[circleNumber + 2].setRadius((int)(Math.random() * (circles[0].getRadius() / 3.5) + 1));
        circles[circleNumber + 2].setCenterX(circles[0].getCenterX());
        circles[circleNumber + 2].setCenterY(circles[0].getCenterY() + (int)(circles[0].getRadius() / 2));
        circles[circleNumber + 2].setFill(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 0));
        circles[circleNumber + 2].setStroke(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 1));
        return circles;
    }
}
