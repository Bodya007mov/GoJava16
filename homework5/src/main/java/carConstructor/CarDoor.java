package carConstructor;

public class CarDoor {

    private boolean door;
    private boolean window;

    CarDoor() {
        this.door = false;
        this.window = false;
    }

    CarDoor(boolean door, boolean window){
        this.door = door;
        this.window = window;
    }

    public void openDoor(){
        this.door = true;
    }

    public void closeDoor(){
        this.door = false;
    }

    public void toggleDoor(){
        this.door = !door;
    }

    public void openWindow(){
        this.window = true;
    }

    public void closeWindow(){
        this.window = false;
    }

    public void toggleWindow(boolean window){
        this.window = !window;
    }

    @Override
    public String toString(){
        return "Door: " + door + " Window: " + window;
    }

}
