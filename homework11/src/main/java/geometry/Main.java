package geometry;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        Button button = new Button("Multi Threads");
        Pane root = new Pane();
        button.setOnAction(event -> {
            for (int i = 0; i < (int)(Math.random() * 10 + 3); i++) {
                Rectangle rectangle = new Rectangle(Math.random() * 200 + 100, Math.random() * 200 + 100);
                rectangle.setX(Math.random() * (1000 - rectangle.getWidth()));
                rectangle.setY(Math.random() * (700 - rectangle.getHeight()));
                rectangle.setFill(Color.rgb((int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255), 0.5));
                root.getChildren().add(rectangle);
                new Thread(new MultiThreadRectangles(rectangle)).start();
            }

        });

        root.getChildren().add(button);

        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.setResizable(false);
        primaryStage.setTitle("Rectangles");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
