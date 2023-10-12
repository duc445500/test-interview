package momo.homework;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import momo.homework.commons.InputOutputFileStream;
import momo.homework.objects.Bills;
import momo.homework.objects.Customers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Unit test for simple App.
 */
public class MomoApplicationTest {

    private static MomoApplication momoApplication;

    @Before
    public void setup(){
        momoApplication = new MomoApplication();
        momoApplication.main(new String[]{"CASH_IN", "1000000"});
    }

    @After
    public void finish(){
        momoApplication.main(new String[]{"EXIT"});
    }

    @Test
    public void testCashInSuccess() {

        momoApplication.main(new String[]{"CASH_IN", "1000000"});
    }

    @Test
    public void testListBills() {
        momoApplication.main(new String[]{"LIST_BILL"});
    }

    @Test
    public void testPayBillsSuccess() {
        momoApplication.main(new String[]{"PAY", "1"});
    }

    @Test
    public void testPayBillsFail_NotEnoughAvailableBalance() {
        momoApplication.main(new String[]{"PAY", "1"});
        momoApplication.main(new String[]{"PAY", "2", "3"});
    }

    @Test
    public void testPayBillsFail_billIdNotExisted() {
        momoApplication.main(new String[]{"PAY", "10", "12"});
    }

    @Test
    public void testDueDate() {
        momoApplication.main(new String[]{"DUE_DATE"});
    }

    @Test
    public void testSchedulePayBillSuccess() {
        momoApplication.main(new String[]{"SCHEDULE", "2", "20/10/2023"});
    }

    @Test
    public void testListPayment() {
        momoApplication.main(new String[]{"LIST_PAYMENT"});
    }

    @Test
    public void testSearchBillsByProvider() {
        momoApplication.main(new String[]{"SEARCH_BILL_BY_PROVIDER", "VNPT"});
    }

    @Test
    public void testExit() {
        momoApplication.main(new String[]{"EXIT"});
    }

    @Test
    public void testExceptionCases(){
        momoApplication.main(new String[]{"SCHEDULE", "2", "10/21/2023"});
        momoApplication.main(new String[]{"SCHEDULE", "10", "10/10/2023"});
        momoApplication.main(new String[]{"CASH_IN", "-200000"});
    }

}
