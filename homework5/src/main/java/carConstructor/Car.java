package carConstructor;

import java.util.Arrays;
import java.util.Scanner;

public class Car {

    private final int year;
    private final String engine;
    private final double maxSpeed;
    private final double racingTime;
    private final int passengerCapacity;
    private int currentPassengers;
    private double currentSpeed;
    private CarDoor[] carDoors;
    private CarWheel[] carWheels;

    Car(int year){
        this.year = year;
        this.engine = "V6";
        this.maxSpeed = 240;
        this.racingTime = 10;
        this.passengerCapacity = 4;
    }

    Car(int year, String engine, double maxSpeed, double racingTime, int passengerCapacity, int currentPassengers){
        this.year = year;
        this.engine = engine;
        this.maxSpeed = maxSpeed;
        this.racingTime = racingTime;
        this.passengerCapacity = passengerCapacity;
        this.currentPassengers = currentPassengers;
    }

    private final Scanner scanner = new Scanner(System.in);

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public void changeCurrentSpeed(double currentSpeed){
        this.currentSpeed += currentSpeed;
    }

    public void putPassenger(){
        if (currentPassengers < passengerCapacity){
            this.currentPassengers++;
        } else {
            System.out.println("The car is full!");
        }
    }

    public void pullPassenger(){
        if (currentPassengers > 0){
            this.currentPassengers--;
        } else {
            System.out.println("The car is empty!");
        }
    }

    public void pullAllPassengers(){
        this.currentPassengers = 0;
    }

    public CarDoor getCarDoor(int i){
        return carDoors[i];
    }

    public CarWheel getCarWheel(int i){
        return carWheels[i];
    }

    public CarDoor[] getCarDoors() {
        return carDoors;
    }

    public void setCarDoors(CarDoor[] carDoors) {
        this.carDoors = carDoors;
    }

    public CarWheel[] getCarWheels() {
        return carWheels;
    }

    public void setCarWheels(CarWheel[] carWheels) {
        this.carWheels = carWheels;
    }

    public void removeWheels(){
        this.carWheels = null;
    }

    public void addCarWheels(CarWheel[] carWheels, int x){
        this.carWheels = new CarWheel[carWheels.length + x];
        System.arraycopy(carWheels, 0, this.carWheels, 0, carWheels.length);
        for (int i = carWheels.length; i < this.carWheels.length; i++) {
            System.out.println("\"1\" to create a random wheel\n\"2\" to create a custom wheel");
            System.out.print("Enter a number: ");
            int wheel = scanner.nextInt();
            if (wheel == 1){
                this.carWheels[i] = new CarWheel();
            } else if (wheel == 2){
                System.out.print("Enter wheel condition in from 0% to 100%: ");
                double wheelCondition = scanner.nextDouble();
                this.carWheels[i] = new CarWheel(wheelCondition/100);
            }
        }
    }

    public double getMinWheel(CarWheel[] carWheels){
        double minWheel = carWheels[0].getWheel();
        for (CarWheel i: carWheels) {
            if(i.getWheel() < minWheel) minWheel = i.getWheel();
        }
        return minWheel;
    }

    public double currentMaxSpeed(){
        if(currentPassengers == 0){
            return 0;
        } else {
            return maxSpeed * getMinWheel(carWheels);
        }
    }

    @Override
    public String toString(){
        return "Year: " + year + "\n" + "Engine: " + engine + "\n" + "MaxSpeed: " + maxSpeed + "\n" +
                "RacingTime: " + racingTime + "\n" + "PassengerCapacity: " + passengerCapacity + "\n" +
                "CurrentPassengers: " + currentPassengers + "\n" + "CurrentSpeed: " + currentSpeed + "\n" +
                "CarDoors: " + Arrays.toString(carDoors) + "\n" + "CarWheels: " + Arrays.toString(carWheels);
    }
}
