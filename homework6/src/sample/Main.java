package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.util.Scanner;

public class Main extends Application {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void start(Stage primaryStage) {
        System.out.println("Enter number of circles: ");
        int circleNumber = scanner.nextInt();
        System.out.println("Enter min radius: ");
        double minRadius = scanner.nextDouble();
        System.out.println("Enter max radius: ");
        double maxRadius = scanner.nextDouble();
        Circle[] circles = CircleConstructor.getCircles(circleNumber, minRadius, maxRadius);
        Pane layout = new Pane();
        for (Circle circle : circles) {
            layout.getChildren().add(circle);
        }
        primaryStage.setScene(new Scene(layout, 600, 550));
        primaryStage.setTitle("Snowman");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
