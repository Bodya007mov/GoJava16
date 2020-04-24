package carConstructor;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Main main = new Main();
        main.start();

    }

    private final Scanner scanner = new Scanner(System.in);

    public void start(){
        Car car = initCar();
        CarDoor[] carDoors = initDoors();
        car.setCarDoors(carDoors);
        for (int i = 0; i < carDoors.length; i++) {
            carDoors[i] = new CarDoor();
        }
        CarWheel[] carWheels = initWheels();
        car.setCarWheels(carWheels);
        for (int i = 0; i < carWheels.length; i++) {
            System.out.println("\"1\" to create a random wheel\n\"2\" to create a custom wheel");
            System.out.print("Enter a number: ");
            int wheel = scanner.nextInt();
            if (wheel == 1){
                carWheels[i] = new CarWheel();
            } else if (wheel == 2){
                System.out.print("Enter wheel condition in from 0% to 100%: ");
                double wheelCondition = scanner.nextDouble();
                carWheels[i] = new CarWheel(wheelCondition/100);
            }

        }
        System.out.println("Your car is ready to drive!");
        System.out.print("Are u sure u have enough wheels?\nEnter a number wheels to be added: ");
        int wheels = scanner.nextInt();
        car.addCarWheels(car.getCarWheels(), wheels);
        driveSimulator(car);
    }

    private void driveSimulator(Car car){
        System.out.println("Choose the door to open!");
        System.out.print("Enter a number of door from \"1\" to \"" + car.getCarDoors().length + "\" : ");
        int index = scanner.nextInt();
        car.getCarDoor(index - 1).openDoor();
        System.out.print("Enter \"1\" to get in the car: ");
        if(scanner.nextInt() == 1) {
            car.putPassenger();
        }
        do{
            System.out.println("Current MaxSpeed is: " + car.currentMaxSpeed());
            System.out.println("Enter a speed in km/h to accelerate: ");
            double speed = scanner.nextDouble();
            car.changeCurrentSpeed(speed);
            if (car.getCurrentSpeed() > car.currentMaxSpeed()){
                car.setCurrentSpeed(car.currentMaxSpeed());
            }
            for (int i = 0; i < car.getCarWheels().length; i++) {
                car.getCarWheel(i).eraseWheel(Math.random()*10);
            }
        } while (car.getCurrentSpeed() > 0);
        System.out.println("Choose the door to close!");
        System.out.print("Enter a number of door from \"1\" to \"" + car.getCarDoors().length + "\" : ");
        index = scanner.nextInt();
        car.getCarDoor(index - 1).closeDoor();
        System.out.print("Enter \"0\" to get out the car: ");
        if(scanner.nextInt() == 0) {
            car.pullPassenger();
        }
        System.out.println(car.toString());
    }

    private Car initCar(){
        System.out.println("Welcome to the Car Salon!");
        System.out.println("\"1\" to choose car by the year\n\"2\" to choose car by more parameters");
        System.out.print("Enter number: ");
        int chosenConstructor = scanner.nextInt();
        if (chosenConstructor == 1) {
            System.out.println("Choose a car from \"2000\" to \"2020\" year");
            System.out.print("Enter the year: ");
            int year = scanner.nextInt();
            return new Car(year);
        } else {
            System.out.print("Enter the year from \"2000\" to \"2020\": ");
            int year = scanner.nextInt();
            System.out.print("Enter engine type: ");
            String engine = scanner.next();
            System.out.print("Enter Max Speed from \"150\" to \"300\" in km/h: ");
            double maxSpeed = scanner.nextDouble();
            System.out.print("Enter racing time to 100 km/h from \"5\" to \"15\" in s: ");
            double racingTime = scanner.nextDouble();
            System.out.print("Enter passengers capacity from \"1\" to \"10\": ");
            int passengerCapacity = scanner.nextInt();
            System.out.print("Enter current passengers from \"1\" to \"10\": ");
            int currentPassengers = scanner.nextInt();
            return new Car(year, engine, maxSpeed, racingTime, passengerCapacity, currentPassengers);
        }

    }

    private CarDoor[] initDoors(){
        System.out.print("Enter number of doors: ");
        int n = scanner.nextInt();
        return new CarDoor[n];
    }

    private CarWheel[] initWheels(){
        System.out.print("Enter number of wheels: ");
        int n = scanner.nextInt();
        return new CarWheel[n];
    }

}
