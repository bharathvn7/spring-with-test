package business;

import data.SomeDataService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SomeDataServiceStub implements SomeDataService {

    @Override
    public int[] retrieveAllData() {
        return new int[] {1,2,3};
    }
}
public class SomeBusinessStubTest {

    @Test
    public void should_calculate_sum_basic(){

        SomeBusinessImpl business = new SomeBusinessImpl();
        business.setSomeDataService(new SomeDataServiceStub());
        int got = business.calculateSumUsingDataService();
        int want = 6;
        assertEquals(want,got);

    }
}
