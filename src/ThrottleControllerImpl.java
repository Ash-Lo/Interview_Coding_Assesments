/*
This class implements ThrottleController interface. Due to ambiguity in the relationship between currentSpeed, cruiseSpeed and 
throttleSpeed the following assumptions have been made

ASSUMPTIONS - 
1. cruiseSpeed is the speed we wish to maintain. currentSpeed is the input and throttleSpeed is the variable under control of us to 
impact currentSpeed, however exact relation unknown.  
2. The problem description given mentions an "INPUT" variable to the quantize function, that is ASSUMED to be 
"currentSpeed-cruiseSpeed"
3. Range variable is declared as a private. LLD did not mention any method to update this value. If required a simple SETTER method
can be used to set the range value
*/

public class ThrottleControllerImpl implements ThrottleController {

    private int cruiseSpeed;
    private int prevThrottlevalue;
    private int range = 2;

    Quantizer quantizer = new QuantizerImpl();

    public int getCruiseSpeed() {
        return cruiseSpeed;
    }

    @Override
    public void setCruiseSpeed(int cruiseSpeed) {
        this.cruiseSpeed = cruiseSpeed;
        return;
    }

    @Override
    public int calculateThrottle(int currentSpeed) {
        int throttleSpeed = quantizer.quantize(currentSpeed - cruiseSpeed, prevThrottlevalue, range);
        prevThrottlevalue = throttleSpeed;
        return throttleSpeed;
    }

}
