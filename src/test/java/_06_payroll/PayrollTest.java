package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given

    	int a = 5;
    	int b = 7;
    	
        //when
    	
    	int bla = 35;

        //then
    	
    	assertEquals(bla, new Payroll().calculatePaycheck(a, b));
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given

        //when

        //then
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given

        //when

        //then
    }

}