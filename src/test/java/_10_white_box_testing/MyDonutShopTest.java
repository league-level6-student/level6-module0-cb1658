package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

    MyDonutShop myDonutShop;
    
    @Mock 
    PaymentService paymentService;
    
    @Mock
    DeliveryService deliveryService;
    
    @Mock
    BakeryService bakeryService;
    
    
    @Mock
    Order o;

    @BeforeEach
    void setUp() {
    	MockitoAnnotations.openMocks(this);
    	
    	myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	
    	Order o = new Order("123" , "floppy", 3, 0.12039123, "Bob", true);

        //when
    	
    	myDonutShop.openForTheDay();
    	
    	when(bakeryService.getDonutsRemaining()).thenReturn(300000);
    	when(paymentService.charge(o)).thenReturn(true);
    	
    	
    	myDonutShop.takeOrder(o);

        //then
    	
    	verify(bakeryService, times(1)).removeDonuts(o.getNumberOfDonuts());
    	verify(deliveryService, times(1)).scheduleDelivery(o);
    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() {
        
    	Order o = new Order("123" , "floppy", 3, 0.12039123, "Bob", true);

        //when
    	
    	myDonutShop.openForTheDay();
    	
    	when(bakeryService.getDonutsRemaining()).thenReturn(0);
    	
    	
        //then
    	Throwable t = assertThrows(Exception.class, () -> myDonutShop.takeOrder(o));
    	assertEquals(t.getMessage(), "Insufficient donuts remaining");
    	verify(bakeryService, never()).removeDonuts(o.getNumberOfDonuts());
    	
    }

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        
    	Order o = new Order("123" , "floppy", 3, 0.12039123, "Bob", true);

        //when
    	
    	myDonutShop.closeForTheDay();
    	
    	when(bakeryService.getDonutsRemaining()).thenReturn(3000);
    	
    	//then
    	
    	Throwable t = assertThrows(Exception.class, () -> myDonutShop.takeOrder(o));
    	assertEquals(t.getMessage(), "Sorry we're currently closed");
    	verify(bakeryService, never()).removeDonuts(o.getNumberOfDonuts());
    }

}