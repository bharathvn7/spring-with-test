package business;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SomeBusinessTest {

    @Test
    public void should_calculate_sum_basic(){

        SomeBusinessImpl business = new SomeBusinessImpl();
        int got = business.calculateSum(new int[] {1,2,3});
        int want = 6;
        assertEquals(want,got);

    }
}
