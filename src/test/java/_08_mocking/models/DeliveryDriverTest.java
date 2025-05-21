package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;
    
    @Mock
    CellPhone cp;
    
    @Mock
    Car c;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	deliveryDriver = new DeliveryDriver("asdfsadf", c, cp);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	
    	boolean expectedWasteOfTime = true;
    	
        //when
    	
    	when(cp.browseCatMemes()).thenReturn(true);

        //then
    	
    	boolean actualWasteOfTime = deliveryDriver.wasteTime();
    	
    	assertEquals(expectedWasteOfTime, actualWasteOfTime);
    	
    }

    @Test
    void itShouldRefuel() {
        //given
    	
    	boolean yes = true;

        //when
    	
    	when(c.fillTank(5)).thenReturn(true);

        //then
    	
    	boolean maybe = deliveryDriver.refuel(5);
    	
    	assertEquals(yes, maybe);
    }

    @Test
    void itShouldContactCustomer() {
        //given

    	boolean yes = true;
    	
        //when
    	
    	when(cp.call("a")).thenReturn(true);

        //then
    	
    	boolean maybe = deliveryDriver.contactCustomer("a");
    	
    	assertEquals(yes, maybe);
    }

}