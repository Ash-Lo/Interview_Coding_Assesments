public interface ThrottleController {
    void setCruiseSpeed(int cruiseSpeed);

    int calculateThrottle(int currentSpeed); // Uses current speed and cruise speed to calculate throttle speed
}