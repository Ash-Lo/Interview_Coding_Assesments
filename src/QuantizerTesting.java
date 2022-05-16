import static org.junit.Assert.*;

import org.junit.Test;

public class QuantizerTesting {

    Quantizer test = new QuantizerImpl();

    // Input value is far away from current throttle value
    @Test
    public void regularTest() {
        assertEquals(6, test.quantize(55, 1, 2));

        assertEquals(2, test.quantize(19, 5, 2));
    }

    // Behavior around border values
    @Test
    public void borderTest() {
        assertEquals(0, test.quantize(-8, 3, 2));
        assertEquals(7, test.quantize(71, 3, 2));
    }

    // Hystersis functionality
    @Test
    public void rangeTest() {
        // Here input is 32 and previous throttle value is 3 and range is 2 so the
        // output should not change
        assertEquals(3, test.quantize(32, 3, 2));

        // Same as previous but the input is outside the range, so output will change
        assertEquals(4, test.quantize(33, 3, 2));

        // Here input is 19, so value should be 2 but it is within the range so output
        // is 3 as expected
        assertEquals(3, test.quantize(19, 3, 2));

        // Here input is 15, which is outside the range, so output changes to 2
        assertEquals(2, test.quantize(15, 3, 2));
    }

}
