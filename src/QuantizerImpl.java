public class QuantizerImpl implements Quantizer {
    @Override
    public int quantize(int input, int throttle, int range) {

        // Base cases
        int quantizedSpeed = 0;
        if (input > 70) {
            quantizedSpeed = 7;
        } else if (input < 0) {
            quantizedSpeed = 0;
        }

        // Regular operation
        else {
            quantizedSpeed = 1 + (int) (Math.floor(input / 10));
        }

        System.out.println(quantizedSpeed);
        System.out.println(throttle);
        int lower = 0, higher = 0;

        // If the floor'd value and the current throttle value are same -> no need to
        // change
        if (quantizedSpeed == throttle) {
            return throttle;
        }

        // If the floor'd value very far away from current value, then the range
        // argument does not come into picture and we simply out the floor'd value
        else if (Math.abs(quantizedSpeed - throttle) > 1) {
            return quantizedSpeed;
        }

        // If the floor'd value is close to the throttle value, then use range argument
        // to evaluate if hystersis is there and value change is not required
        else {
            higher = 10 * throttle + range;
            lower = 10 * (throttle - 1) - range;

            // Throttle = 3 & range = 2, then for any input between 18-32 the output value
            // should not change
            if (input >= lower && input <= higher) {
                return throttle;
            } else {
                return quantizedSpeed;
            }
        }
    }
}
