package business;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class ListMockTest {

    List<String> mock = mock(List.class);

    @Test
    public void size_basic_test() {
        when(mock.size()).thenReturn(5);
        assertEquals(5,mock.size());
    }

    @Test
    public void return_different_values_test() {
        when(mock.size()).thenReturn(5).thenReturn(10);
        assertEquals(5,mock.size());
        assertEquals(10,mock.size());
    }

    @Test
    public void return_with_parameters_test() {
        when(mock.get(0)).thenReturn("Passed");
        assertEquals("Passed",mock.get(0));
    }

    @Test
    public void return_with_generic_parameters() {
        when(mock.get(anyInt())).thenReturn("Passed");
        assertEquals("Passed",mock.get(0));
        assertEquals("Passed",mock.get(1));
    }

    @Test
    public void verification_basics(){
        //SUT
        String value1 = mock.get(0);
        String value2 = mock.get(1);

        //Verify
        verify(mock).get(0);

        verify(mock, times(2)).get(anyInt());
        verify(mock, atLeast(1)).get(anyInt());
        verify(mock, atLeastOnce()).get(anyInt());
        verify(mock, atMost(2)).get(anyInt());
        verify(mock, never()).get(2);
    }

    @Test
    public void argumentCapturing(){
        //SUT
        mock.add("SomeString");

        //verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock).add(captor.capture());

        assertEquals("SomeString", captor.getValue());

    }

    @Test
    public void multipleArgumentCapturing(){
        //SUT
        mock.add("SomeString1");
        mock.add("SomeString2");

        //verification
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        verify(mock, times(2)).add(captor.capture());

        List<String> allValues = captor.getAllValues();
        assertEquals("SomeString1", allValues.get(0));
        assertEquals("SomeString2", allValues.get(1));

    }

    @Test
    public void mocking(){
        ArrayList arrayListMock = mock(ArrayList.class);
        arrayListMock.get(0); //null
        arrayListMock.size(); // 0
        arrayListMock.add("Test");
        arrayListMock.add("Test2");
        arrayListMock.size(); // 0
        when(arrayListMock.size()).thenReturn(5);
        arrayListMock.size(); // 5
    }

    @Test
    public void spying(){
        ArrayList arrayListSpy = spy(ArrayList.class);

        arrayListSpy.add("Test0");

        arrayListSpy.get(0); //Test0
        arrayListSpy.size(); // 1

        arrayListSpy.add("Test");
        arrayListSpy.add("Test2");

        arrayListSpy.size(); // 3

        when(arrayListSpy.size()).thenReturn(5);
        arrayListSpy.size(); // 5

        arrayListSpy.add("Test3");
        arrayListSpy.size(); // 5

        //verify(arrayListSpy).add("Test4");
    }

}
