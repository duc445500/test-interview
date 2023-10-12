package momo.homework;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import momo.homework.commons.InputOutputFileStream;
import momo.homework.objects.Bills;
import momo.homework.objects.Customers;
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
    }

    @Test
    public void testSwitchFuncs() {

        momoApplication.main(new String[]{"CASH_IN", "1000000"});
        momoApplication.main(new String[]{"LIST_BILL"});
        momoApplication.main(new String[]{"PAY", "1"});
        momoApplication.main(new String[]{"PAY", "2", "3"});
        momoApplication.main(new String[]{"PAY", "10"});
        momoApplication.main(new String[]{"DUE_DATE"});
        momoApplication.main(new String[]{"SCHEDULE", "2", "20/10/2023"});
        momoApplication.main(new String[]{"LIST_PAYMENT"});
        momoApplication.main(new String[]{"SEARCH_BILL_BY_PROVIDER", "VNPT"});
        momoApplication.main(new String[]{"EXIT"});
    }

    @Test
    public void testExceptions(){
        momoApplication.main(new String[]{"SCHEDULE", "2", "10/21/2023"});
        momoApplication.main(new String[]{"CASH_IN", "-200000"});


    }

}
