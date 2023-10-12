package momo.homework.services;

import momo.homework.objects.Bills;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentBillService {

    void cashIn(String amount) throws Exception;

    List<Bills> getBills();

    void payBills(long[] billId);

    void schedulePayBill(String billId, String dateString);

    void getPaymentHistory();

    List<Bills> getUnPaidBills();

    List<Bills> getBillsByProvider(String provider);
}
