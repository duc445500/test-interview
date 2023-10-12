package momo.homework.objects;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Customers implements Serializable {

    private static final long serialVersionUID = 7945709685127280822L;
    private Double availableBalance;
    private List<Bills> bills;

    public Customers(Double accountBalance, List<Bills> bills) {
        this.availableBalance = accountBalance;
        this.bills = bills;
    }

    public Customers() {
        this.availableBalance = 0d;
        this.bills = new ArrayList<>();
    }

    public Double getAvailableBalance() {
        return this.availableBalance;
    }

    public void addBalance(Double amount) {
        this.availableBalance += amount;
    }

    public List<Bills> getBills(){
        return this.bills;
    }

    public void addBill(Bills bill){
        if (bill != null){
            this.bills.add(bill);
        }
    }

    public void removeBill(long billId){
        this.bills.removeIf(bill -> bill.getId() == billId);
    }

    public void payBills(Bills bill){
        bill.setPaid(true);
        this.availableBalance -= bill.getAmount();
        bill.setPaymentDate(LocalDate.now());
    }
}
