package momo.homework.services.impl;


import momo.homework.commons.InputOutputFileStream;
import momo.homework.objects.Bills;
import momo.homework.objects.Customers;
import momo.homework.services.PaymentBillService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * class PaymentBillServiceImpl responsibility to handle customer's request aim to collect data or make payment for bills
 */
public class PaymentBillServiceImpl implements PaymentBillService {

    private Customers customer;

    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PaymentBillServiceImpl(Customers customer) {
        this.customer = customer;
    }

    @Override
    public void cashIn(String amount) throws Exception {
        if (amount != null && Double.parseDouble(amount) > 0) {
            System.out.println(amount);
            customer.addBalance(Double.parseDouble(amount));
            System.out.println("Your available balance: " + customer.getAvailableBalance());
            InputOutputFileStream.storeCustomersToFile(customer);
        } else {
            throw new Exception("The additional amount is invalid. Please check again!");
        }
    }

    @Override
    public List<Bills> getBills() {
        List<Bills> bills = customer.getBills();
        printBills(bills);
        return bills;
    }

    @Override
    public void payBills(long[] billId) {
        //        this.availableBalance = BigDecimal.valueOf(1000000000);
        this.getUnPaidBills().stream().filter(bill -> !bill.isPaid()).forEach(bill ->{
            for (long id: billId) {
                if (bill.getId() == id) {
                    if (customer.getAvailableBalance().compareTo(bill.getAmount()) >= 0) {
                        customer.payBills(bill);
                        System.out.println("Payment has been completed for Bill with id " + billId);
                    } else {
                        System.out.println("Sorry! Not enough fund to proceed with payment.");
                    }
                } else {
                    System.out.println("Sorry! Not found a bill with such id");
                }
            }
        });
        System.out.println("Your current balance is: " + customer.getAvailableBalance());
        InputOutputFileStream.storeCustomersToFile(customer);
    }

    @Override
    public void schedulePayBill(String billId, String dateString) {
        if (billId != null && dateString != null && !dateString.isEmpty()){
            try {
                LocalDate dueDate = LocalDate.parse(dateString,dateTimeFormatter);
                Bills bill = customer.getBills().stream().filter(billTemp -> billTemp.getId() == Long.parseLong(billId)).findFirst().get();
                bill.setPaymentDate(dueDate);
                System.out.println("Payment for bill id " + Long.parseLong(billId) + " is scheduled on " + dateString);
                InputOutputFileStream.storeCustomersToFile(customer);
            } catch (Exception e) {
                System.out.println("Error occurred when trying to schedule for a bill");
//                log.debug(e.getMessage());
            }

        }
    }

    @Override
    public void getPaymentHistory() {
        List<Bills> bills = customer.getBills().stream()
                .filter(bill -> bill.getPaymentDate() != null).collect(Collectors.toList());
        if (bills != null || !bills.isEmpty())
            printPaymentHistory(bills);
    }

    @Override
    public List<Bills> getUnPaidBills() {
        List<Bills> unPaidBills = customer.getBills().stream().filter(bill -> !bill.isPaid()).collect(Collectors.toList());
        printBills(unPaidBills);
        return unPaidBills;
    }

    @Override
    public List<Bills> getBillsByProvider(String provider) {
        List<Bills> billSearchByProvider = customer.getBills().stream().filter(bill -> bill.getProvider().equalsIgnoreCase(provider)).collect(Collectors.toList());
        printBills(billSearchByProvider);
        return billSearchByProvider;
    }

    private void printBills(List<Bills> bills){
        String header = String.format("%-12s%-12s%-12s%-12s%-12s%-12s", "Bill No.", "Type","Amount", "Due Date", "State", "PROVIDER");
        System.out.println(header);
        for (Bills bill : bills) {
            String row = String.format("%-12d%-12s%-12.0f%-12s%-12s%-12s",bill.getId(),bill.getType(),bill.getAmount(),
                    bill.getDueDate().format(dateTimeFormatter), bill.isPaid() ? "PROCESSED" : "NOT_PAID", bill.getProvider());
            System.out.println(row);
        }
    }

    private void printPaymentHistory(List<Bills> bills){
        String header = String.format("%-12s%-12s%-12s%-12s%-12s", "No.", "Amount", "Payment Date", "State", "Bill Id");
        System.out.println(header);
        int i = 1;
        for (Bills bill : bills) {
            String row = String.format("%-12d%-12.0f%-12s%-12s%-12d",i,bill.getAmount(),bill.getPaymentDate().format(dateTimeFormatter),
                    bill.isPaid() ? "PROCESSED" : "PENDING", bill.getId());
            System.out.println(row);
            i++;
        }
    }
}
