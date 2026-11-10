public class Car {
    // Cruise control limits
    private static final double MIN_CRUISE_SPEED = 20;
    private static final double MAX_CRUISE_SPEED = 160;

    private String typeName;
    private double speed;
    private double gasolineLevel;
    private double tankCapacity;

    private double targetSpeed;
    private boolean cruiseControlOn;

    // Original constructor
    public Car(String typeName) {
        this.typeName = typeName;
        this.speed = 0;
        this.gasolineLevel = 0;
        this.tankCapacity = 100;
    }

    // Task 2: additional constructor with tank capacity and speed
    public Car(String typeName, double tankCapacity, double speed) {
        this.typeName = typeName;
        this.tankCapacity = tankCapacity;
        this.speed = speed;
        this.gasolineLevel = 0;
    }

    public void accelerate() {
        if (gasolineLevel > 0) {
            speed += 10;
            gasolineLevel = Math.max(0, gasolineLevel - 1); // accelerating uses fuel
        } else {
            speed = 0;
        }
    }

    public void decelerate(double amount) {
        if (gasolineLevel > 0) {
            if (amount > 0) {
                speed = Math.max(0, speed - amount);
            }
        } else {
            speed = 0;
        }
    }

    public void fillTank() {
        gasolineLevel = tankCapacity;
    }

    public double getSpeed() {
        return speed;
    }

    public double getGasolineLevel() {
        return gasolineLevel;
    }

    public String getTypeName() {
        return typeName;
    }

    // Task 4: cruise control

    // Returns false if the target speed is outside the allowed range
    public boolean setTargetSpeed(double newTargetSpeed) {
        if (newTargetSpeed < MIN_CRUISE_SPEED || newTargetSpeed > MAX_CRUISE_SPEED) {
            return false;
        }
        targetSpeed = newTargetSpeed;
        return true;
    }

    public double getTargetSpeed() {
        return targetSpeed;
    }

    public boolean isCruiseControlOn() {
        return cruiseControlOn;
    }

    // Returns true if the target speed was reached, false if it was not possible
    public boolean turnCruiseControlOn() {
        if (targetSpeed < MIN_CRUISE_SPEED || gasolineLevel <= 0) {
            cruiseControlOn = false;
            return false;
        }

        cruiseControlOn = true;

        // Accelerate until the target is reached
        while (speed < targetSpeed) {
            if (gasolineLevel <= 0) {
                cruiseControlOn = false; // ran out of fuel, so give up
                return false;
            }
            accelerate();
        }

        // Decelerate if too fast (also fixes overshooting after accelerating)
        if (speed > targetSpeed) {
            decelerate(speed - targetSpeed);
        }

        return true;
    }

    public void turnCruiseControlOff() {
        cruiseControlOn = false;
    }
}