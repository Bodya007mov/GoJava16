package geometry;

import javafx.scene.shape.Rectangle;

public class MultiThreadRectangles implements Runnable{

    Rectangle rectangle;

    public MultiThreadRectangles(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void run() {
        double x;
        double y;
        boolean flagX = true;
        boolean flagY = true;
        for(int i = 0; i < 5000; i++) {
            try {
                if(flagX) {
                    if((x = rectangle.getX()) < (1000 - rectangle.getWidth())) {
                        rectangle.setX(++x);
                    } else {
                        flagX = false;
                    }
                } else {
                    if((x = rectangle.getX()) > 0) {
                        rectangle.setX(--x);
                    } else {
                        flagX = true;
                    }
                }
                Thread.sleep(1);
                if(flagY) {
                    if((y = rectangle.getY()) < (700 - rectangle.getHeight())) {
                        rectangle.setY(++y);
                    } else {
                        flagY = false;
                    }
                } else {
                    if((y = rectangle.getY()) > 0) {
                        rectangle.setY(--y);
                    } else {
                        flagY = true;
                    }
                }
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
