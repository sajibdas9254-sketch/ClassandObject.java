public class CarDriver {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota Corolla");
        myCar.fillTank();

        for (int i = 0; i < 6; i++) {
            myCar.accelerate();
            System.out.println(myCar.getTypeName() + ": speed is " + myCar.getSpeed() + " km/h");
        }

        // test1: target speed outside the allowed range
        if (!myCar.setTargetSpeed(250)) {
            System.out.println("Target speed 250 km/h is not allowed");
        }

        // Test 2: accelerate to the target speed
        myCar.setTargetSpeed(100);
        if (myCar.turnCruiseControlOn()) {
            System.out.println("Cruise control on, target " + myCar.getTargetSpeed()
                    + " km/h, speed is " + myCar.getSpeed() + " km/h");
        }

        // Test 3: decelerate to a lower target speed
        myCar.setTargetSpeed(45);
        if (myCar.turnCruiseControlOn()) {
            System.out.println("Cruise control on, target " + myCar.getTargetSpeed()
                    + " km/h, speed is " + myCar.getSpeed() + " km/h");
        }

        myCar.turnCruiseControlOff();
        System.out.println("Cruise control on: " + myCar.isCruiseControlOn());

        // Test 4: target cannot be reached (small tank, uses the Task 2 constructor)
        Car smallCar = new Car("Small car", 3, 0);
        smallCar.fillTank();
        smallCar.setTargetSpeed(100);
        if (!smallCar.turnCruiseControlOn()) {
            System.out.println(smallCar.getTypeName() + ": could not reach "
                    + smallCar.getTargetSpeed() + " km/h, cruise control turned off (speed "
                    + smallCar.getSpeed() + " km/h)");
        }
    }
}