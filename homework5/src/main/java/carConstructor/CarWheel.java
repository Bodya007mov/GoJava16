package carConstructor;

public class CarWheel {

    private double wheel;

    CarWheel(){
        this.wheel = Math.random();
    }

    CarWheel(double wheel){
        this.wheel = wheel;
    }

    public double getWheel() {
        return wheel;
    }

    public void refreshWheel(){
        this.wheel = 1;
    }

    public void eraseWheel(double x){
        this.wheel *= 1 - x/100;
    }

    @Override
    public String toString(){
        return "Wheel condition in %: " + wheel * 100;
    }

}
