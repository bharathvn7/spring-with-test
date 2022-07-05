package business;

import data.SomeDataService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SomeBusinessMockTest {
    @InjectMocks
    SomeBusinessImpl business = new SomeBusinessImpl();

    @Mock
    SomeDataService dataServiceMock;

    // not required when using @InjectMocks and @Mock and ExtendWith
//    @BeforeEach
//    public void before() {
//        business.setSomeDataService(dataServiceMock);
//    }

    @Test
    public void should_calculate_sum_basic(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {1,2,3});
        int got = business.calculateSumUsingDataService();
        int want = 6;
        assertEquals(want,got);
    }

    @Test
    public void should_calculate_sum_empty(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {});
        int got = business.calculateSumUsingDataService();
        int want = 0;
        assertEquals(want,got);
    }

    @Test
    public void should_calculate_sum_OneValue(){
        when(dataServiceMock.retrieveAllData()).thenReturn(new int[] {5});
        int got = business.calculateSumUsingDataService();
        int want = 5;
        assertEquals(want,got);
    }
}
