package momo.homework;

import momo.homework.commons.InputOutputFileStream;
import momo.homework.objects.Bills;
import momo.homework.objects.Customers;
import momo.homework.services.PaymentBillService;
import momo.homework.services.impl.PaymentBillServiceImpl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Make a main.test.momo test
 * Begin 12:01 PM Oct 12th 2023
 */
public class MomoApplication {

    private static PaymentBillService service;

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public MomoApplication(){
        service = new PaymentBillServiceImpl(setupCustomer());
    }

    public static void main(String[] args) {
        MomoApplication momoApplication = new MomoApplication();
        if (args.length == 0) {
            return;
        }
        switchFuncs(args);

    }

    public static void switchFuncs(String[] args) {
        try {
            switch (args[0]){
                case "CASH_IN":
                    service.cashIn(args[1]);
                    break;
                case "LIST_BILL":
                    service.getBills();
                    break;
                case "PAY":
                    String[] newArgs = Arrays.copyOfRange(args, 1, args.length);
                    long[] billIds = Stream.of(newArgs).mapToLong(Long::parseLong).toArray();

                    service.payBills(billIds);
                    break;
                case "DUE_DATE":
                    service.getUnPaidBills();
                    break;
                case "SCHEDULE":
                    service.schedulePayBill(args[1], args[2]);
                    break;
                case "LIST_PAYMENT":
                    service.getPaymentHistory();
                    break;
                case "SEARCH_BILL_BY_PROVIDER":
                    service.getBillsByProvider(args[1]);
                    break;
                case "EXIT":
                    reSetupCustomer();
                    System.out.println("Good bye!");
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            System.out.println("Please try again with correct information and  statement! " + e.getMessage());
        }
    }

    private Customers setupCustomer(){
        Customers customer = (Customers) InputOutputFileStream.getCustomersFromFile();
        return customer;
    }

    private static Customers reSetupCustomer(){
        Customers customer = new Customers();
        customer.addBill(new Bills(1l, "ELECTRIC", "EVN HCMC", 200000d,  false, null, LocalDate.parse("25/10/2020", dateTimeFormatter)));
        customer.addBill(new Bills(2l, "WATER", "SAVACO HCMC", 175000d, false, null, LocalDate.parse("30/10/2020", dateTimeFormatter)));
        customer.addBill(new Bills(3l, "INTERNET", "VNPT",800000d, false, null, LocalDate.parse("30/11/2020", dateTimeFormatter)));
        InputOutputFileStream.storeCustomersToFile(customer);
        return customer;
    }
}