package sample;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) {
//        System.out.println("Enter number of circles: ");
//        int circleNumber = scanner.nextInt();
//        System.out.println("Enter min radius: ");
//        double minRadius = scanner.nextDouble();
//        System.out.println("Enter max radius: ");
//        double maxRadius = scanner.nextDouble();
//        Circle[] circles = CircleConstructor.getCircles(circleNumber, minRadius, maxRadius);
//        Pane layout = new Pane();
//        for (Circle circle : circles) {
//            layout.getChildren().add(circle);
//        }
//        primaryStage.setScene(new Scene(layout, 600, 550));
//        primaryStage.setTitle("Snowman");
//        primaryStage.show();

        System.out.println("Enter Ox: ");
        int centerX = scanner.nextInt();
        System.out.println("Enter Oy: ");
        int centerY = scanner.nextInt();
        System.out.println("Enter radius: ");
        int radius = scanner.nextInt();

        double a = (int)(Math.sqrt(2 * Math.pow(radius, 2) - 2 * Math.pow(radius, 2) * Math.cos(Math.toRadians(72))));
        double b = (int)(a * Math.cos(Math.toRadians(54)));
        double c = (int)(a * Math.sin(Math.toRadians(54)));
        double d = (int)(Math.sqrt(Math.abs(Math.pow(radius, 2) - Math.pow(a, 2) / 4)));

        double r = (int)((radius - b) / Math.cos(Math.toRadians(36)));
        double f = (int)(Math.sqrt(2 * Math.pow(r, 2) - 2 * Math.pow(r, 2) * Math.cos(Math.toRadians(72))));
        double g = (int)(f * Math.cos(Math.toRadians(54)));
        double h = (int)(f * Math.sin(Math.toRadians(54)));
        double e = (int)(Math.sqrt(Math.abs(Math.pow(r, 2) - Math.pow(f, 2) / 4)));

        String pointA = " " + centerX + " " + (centerY - radius);
        String pointC = " " + (centerX + c) + " " + (centerY - radius + b);
        String pointE = " " + (centerX + a / 2) + " " + (centerY + d);
        String pointG = " " + (centerX - a / 2) + " " + (centerY + d);
        String pointI = " " + (centerX - c) + " " + (centerY - radius + b);

        String pointB = " " + (centerX + f / 2) + " " + (centerY - e);
        String pointD = " " + (centerX + h) + " " + (centerY + r - g);
        String pointF = " " + centerX + " " + (centerY + r);
        String pointH = " " + (centerX - h) + " " + (centerY + r - g);
        String pointJ = " " + (centerX - f / 2) + " " + (centerY - e);

        SVGPath svgPath = new SVGPath();
        String path = "M" + pointA + "L" + pointB + "L" + pointC + "L" + pointD + "L" + pointE +
                        pointF + "L" + pointG + "L" + pointH + "L" + pointI + "L" + pointJ + "z";
        svgPath.setContent(path);
        svgPath.setFill(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 0));
        svgPath.setStroke(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 1));
        Group group = new Group(svgPath);
        primaryStage.setScene(new Scene(group, 600, 550));
        primaryStage.setTitle("Star");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
